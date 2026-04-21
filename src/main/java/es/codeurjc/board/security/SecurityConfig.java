package es.codeurjc.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.codeurjc.board.security.jwt.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
  	private UnauthorizedHandlerJwt unauthorizedHandlerJwt;
    	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public RepositoryUserDetailsService userDetailService;

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
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider()); 
        
        http
			.securityMatcher("/api/v1/**")
			.exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandlerJwt));
		

        http.authorizeHttpRequests(authorize -> authorize.
            anyRequest().permitAll());
		
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
                        .requestMatchers("/", "/assets/css/**", "/assets/images/**", "/error", "/403", "/login", "/check").permitAll()
                        .requestMatchers("/User/register").permitAll()
                        .requestMatchers("/images/*").permitAll()
                        .requestMatchers("/Plants/catalogPlants").permitAll()
                        .requestMatchers("/Plants/favoritePlant/*").permitAll()
                        .requestMatchers("/Plants/viewPlant/*").permitAll()
                        .requestMatchers("/Products/catalogProducts").permitAll()
                        .requestMatchers("/Reviews/forum").permitAll()
                        .requestMatchers("/quizzPlants").permitAll()
                        .requestMatchers("/quizzPlants/result").permitAll()


                        // PRIVATE PAGES
                        .requestMatchers("/User/delete/*").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/User/profile/*").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/User/configuration").hasAnyRole("USER")
                        .requestMatchers("/Plants/ratingPlant").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/Plants/*/delete").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/Plants/viewPlant/*").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/Admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/User/**").hasAnyRole("USER")
                        .requestMatchers("/Plants/**").hasAnyRole("USER")
                        .requestMatchers("/Products/newProduct").hasAnyRole("ADMIN")
                        .requestMatchers("/Products/new").hasAnyRole("ADMIN")
                        .requestMatchers("/Products/editProduct/*").hasAnyRole("ADMIN")
                        .requestMatchers("/products/{id}/delete").hasAnyRole("ADMIN")
                        .requestMatchers("/products/{id}/reactivate").hasAnyRole("ADMIN")
                        .requestMatchers("/Reviews/newreview").hasAnyRole("USER")
                        .requestMatchers("/Reviews/editReview/*").hasAnyRole("USER")
                        .requestMatchers("/Reviews/*/delete").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/Products/*").hasAnyRole("USER")
                        .requestMatchers("/Orders/*/delete").hasAnyRole("ADMIN")
                        .requestMatchers("/Orders/*/status").hasAnyRole("ADMIN")
                        .requestMatchers("/Orders/shoppingCart").hasAnyRole("USER")
                        .requestMatchers("/Orders/payment").hasAnyRole("USER")
                        .requestMatchers("/Orders/checkout").hasAnyRole("USER")
                        .requestMatchers("/Orders/{id}/cancel").hasAnyRole("USER")
                        .requestMatchers("/Orders/success").hasAnyRole("USER")
                        .requestMatchers("/Cart/add/*").hasAnyRole("USER")
                        .requestMatchers("/Cart/remove/*").hasAnyRole("USER")
                        .requestMatchers("/Cart/clear").hasAnyRole("USER")
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