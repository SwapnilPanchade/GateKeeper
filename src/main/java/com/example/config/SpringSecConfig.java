package com.example.config;

import com.example.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    public SpringSecConfig(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())  // Disable CSRF (needed for Postman)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user/register", "/api/user/login").permitAll() // Public
                        .requestMatchers("/api/users/getAllUsers", "/api/roles/**").hasRole("ADMIN") // Admin-only
                        .requestMatchers("/api/users/{id}").hasAnyRole("ADMIN", "MANAGER") // Admin & Manager
                        .requestMatchers("/api/users/godmode").authenticated() // Authenticated users
                        .anyRequest().authenticated() // Everything else requires authentication
                )
                .httpBasic(Customizer.withDefaults()) // Basic Auth (Postman)
                .formLogin(Customizer.withDefaults()) // Form Login (for browser-based testing)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
