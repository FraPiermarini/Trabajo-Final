package com.example.Trabajo.Final.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String token = null;
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            token = header.substring(7);
        }
        if(token == null && request.getCookies() != null){
            token = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
        }
        if(token != null && jwtService.validarToken(token)){
            String email = jwtService.extraerEmail(token);
            String rol = jwtService.extraerRol(token);
            String authorityRole = rol.startsWith("Role") ? rol : "ROLE_" + rol;
            var auth = new UsernamePasswordAuthenticationToken(email, null, List.of(new SimpleGrantedAuthority(authorityRole))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

}
