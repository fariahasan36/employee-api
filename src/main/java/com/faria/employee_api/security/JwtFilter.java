package com.faria.employee_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        if(request.getRequestURI().equals("/auth/token") || request.getRequestURI().startsWith("/actuator")){
            filterChain.doFilter(request,response);
            return;
        }
        System.out.println("Request: " + request.getRequestURI());
        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer ")){

            String token = header.substring(7);


            if (jwtService.isValid(token)) {
                System.out.println("JWT is valid");
                var authentication =
                        new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                                "employee-api-user",
                                null,
                                java.util.List.of()
                        );

                org.springframework.security.core.context.SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);

                filterChain.doFilter(request, response);
                return;
            }
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT");    }
}
