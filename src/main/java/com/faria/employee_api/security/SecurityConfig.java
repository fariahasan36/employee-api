package com.faria.employee_api.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


    private final JwtFilter jwtFilter;


    public SecurityConfig(JwtFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }


    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {


        return http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/auth/token")
                        .permitAll()

                        .requestMatchers("/api/employees/**")
                        .authenticated()

                        .anyRequest()
                        .permitAll()

                )

                .addFilterBefore(
                        jwtFilter,
                        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class
                )

                .build();
    }
}