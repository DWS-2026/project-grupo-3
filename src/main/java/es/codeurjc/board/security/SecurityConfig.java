package es.codeurjc.board.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import es.codeurjc.board.rest.dto.ApiErrorDTO;
import es.codeurjc.board.security.jwt.JwtRequestFilter;
import es.codeurjc.board.security.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RepositoryUserDetailsService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public AuthenticationEntryPoint apiAuthenticationEntryPoint() {
        return (request, response, authException) -> {
            logger.warn("401 en filtro - {}: {}", request.getRequestURI(), authException.getMessage());

            ApiErrorDTO error = new ApiErrorDTO(401, "UNAUTHORIZED",
                "No autenticado. Token de sesión ausente o expirado.", request.getRequestURI());

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(401);

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            response.getWriter().write(mapper.writeValueAsString(error));
        };
    }

    @Bean
    public AccessDeniedHandler apiAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            logger.warn("403 en filtro - {}: {}", request.getRequestURI(), accessDeniedException.getMessage());

            ApiErrorDTO error = new ApiErrorDTO(403, "FORBIDDEN",
                "Acceso denegado. No tienes permisos suficientes para este recurso.", request.getRequestURI());

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(403);

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            response.getWriter().write(mapper.writeValueAsString(error));
        };
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        
        http.authenticationProvider(authenticationProvider());

        http
            .securityMatcher("/api/v1/**")
            .exceptionHandling(handling -> handling
                .authenticationEntryPoint(apiAuthenticationEntryPoint())   
                .accessDeniedHandler(apiAccessDeniedHandler())          
            );

        http.authorizeHttpRequests(authorize -> authorize
                // public
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/refresh").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/logout").permitAll()

                // products
                .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/products/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/products/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasRole("ADMIN")

                // orders
                .requestMatchers(HttpMethod.GET, "/api/v1/orders/my").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/v1/orders/**").hasRole("USER")

                .requestMatchers(HttpMethod.GET, "/api/v1/orders/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/orders/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/orders/**").hasRole("ADMIN")

                // plants
                .requestMatchers(HttpMethod.GET, "/api/v1/plants/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/plants/**").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/v1/plants/**").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/plants/**").hasAnyRole("USER", "ADMIN")

                // reviews
                .requestMatchers(HttpMethod.GET, "/api/v1/reviews/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/reviews/**").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/v1/reviews/**").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/reviews/**").hasAnyRole("USER","ADMIN")

                .requestMatchers("/api/v1/reviews/*/video/**").hasAnyRole("USER", "ADMIN")

                // users
                .requestMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/users/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/users/me").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasRole( "USER")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAnyRole("ADMIN", "USER")

                // images
                .requestMatchers(HttpMethod.GET, "/api/v1/images/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/images/**").hasAnyRole("USER", "ADMIN")

                .anyRequest().authenticated()
        );

        // Disable Form login Authentication
        http.formLogin(formLogin -> formLogin.disable());

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf(csrf -> csrf.disable());

        // Disable Basic Authentication
        http.httpBasic(httpBasic -> httpBasic.disable());

        // Stateless session
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add JWT Token filter
        http.addFilterBefore(new JwtRequestFilter(userDetailService, jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception { //first condition that match is what it applies
        http.authenticationProvider(authenticationProvider());

        http.authorizeHttpRequests(authorize -> authorize
                // PUBLIC PAGES
                .requestMatchers("/", "/assets/css/**", "/assets/images/**", "/error", "/403", "/login", "/check", "/images/*").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs.yaml").permitAll()
                        .requestMatchers("/users/register").permitAll()
                .requestMatchers("/images/*").permitAll()
                        .requestMatchers("/plants/catalogPlants").permitAll()
                        .requestMatchers("/plants/favoritePlant/*").permitAll()
                        .requestMatchers("/plants/viewPlant/*").permitAll()
                        .requestMatchers("/products/catalogProducts").permitAll()
                        .requestMatchers("/reviews/forum").permitAll()
                .requestMatchers("/quizzPlants").permitAll()
                .requestMatchers("/quizzPlants/result").permitAll()
                .requestMatchers("/videos/**").permitAll()
                // PRIVATE PAGES
                        .requestMatchers("/users/delete/*").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/users/profile/*").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/users/configuration").hasAnyRole("USER")
                        .requestMatchers("/plants/ratingPlant").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/plants/*/delete").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/plants/viewPlant/*").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admins/**").hasAnyRole("ADMIN")
                        .requestMatchers("/users/**").hasAnyRole("USER")
                        .requestMatchers("/plants/**").hasAnyRole("USER")
                        .requestMatchers("/products/newProduct").hasAnyRole("ADMIN")
                        .requestMatchers("/products/new").hasAnyRole("ADMIN")
                        .requestMatchers("/products/editProduct/*").hasAnyRole("ADMIN")
                .requestMatchers("/products/{id}/delete").hasAnyRole("ADMIN")
                .requestMatchers("/products/{id}/reactivate").hasAnyRole("ADMIN")
                        .requestMatchers("/reviews/newreview").hasAnyRole("USER")
                        .requestMatchers("/reviews/editReview/*").hasAnyRole("USER")
                        .requestMatchers("/reviews/*/delete").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/products/*").hasAnyRole("USER")
                        .requestMatchers("/orders/*/delete").hasAnyRole("ADMIN")
                        .requestMatchers("/orders/*/status").hasAnyRole("ADMIN")
                        .requestMatchers("/orders/shoppingCart").hasAnyRole("USER")
                        .requestMatchers("/orders/payment").hasAnyRole("USER")
                        .requestMatchers("/orders/checkout").hasAnyRole("USER")
                        .requestMatchers("/orders/{id}/cancel").hasAnyRole("USER")
                        .requestMatchers("/orders/success").hasAnyRole("USER")
                        .requestMatchers("/carts/add/*").hasAnyRole("USER")
                        .requestMatchers("/carts/remove/*").hasAnyRole("USER")
                        .requestMatchers("/carts/clear").hasAnyRole("USER")
        )
                .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .failureUrl("/loginerror")
                .defaultSuccessUrl("/", true)
                .permitAll()
                )
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                )
                .exceptionHandling(exception -> exception
                .accessDeniedPage("/403")
                );
        return http.build();
    }

}
