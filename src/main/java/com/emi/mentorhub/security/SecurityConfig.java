package com.emi.mentorhub.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // Swagger público
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-ui.html"
                ).permitAll()

                // Auth público
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()

                // ADMIN
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // Users
                .requestMatchers(HttpMethod.GET, "/api/users")
                .hasRole("ADMIN")

                // Mentorships
                .requestMatchers(HttpMethod.POST, "/api/mentorships")
                .hasRole("STUDENT")

                .requestMatchers(HttpMethod.GET, "/api/mentorships")
                .hasAnyRole("ADMIN", "MENTOR", "STUDENT")

                .requestMatchers(HttpMethod.PATCH, "/api/mentorships/*/approve")
                .hasRole("MENTOR")

                .requestMatchers(HttpMethod.PATCH, "/api/mentorships/*/reject")
                .hasRole("MENTOR")

                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
