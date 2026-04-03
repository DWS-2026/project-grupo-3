package es.codeurjc.board.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //first condition that match is what it applies
        http.authenticationProvider(authenticationProvider());

        http.authorizeHttpRequests(authorize -> authorize
                        // PUBLIC PAGES
                        .requestMatchers("/", "/assets/css/**", "/assets/images/**",   "/error", "/403", "/login", "/check").permitAll()
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
                        .requestMatchers("/Reviews/newreview").hasAnyRole("USER")
                        .requestMatchers("/Reviews/editReview/*").hasAnyRole("USER")
                        .requestMatchers("/Reviews/*/delete").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/Products/*").hasAnyRole("USER")
                        .requestMatchers("/Orders/*/delete").hasAnyRole("ADMIN")
                        .requestMatchers("/Orders/*/status").hasAnyRole("ADMIN")
                        .requestMatchers("/Orders/shoppingCart").hasAnyRole("USER")
                        .requestMatchers("/Orders/payment").hasAnyRole("USER")
                        .requestMatchers("/Orders/checkout").hasAnyRole("USER")
                        .requestMatchers("/Orders/{id}/cancel").hasAnyRole("USER")
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