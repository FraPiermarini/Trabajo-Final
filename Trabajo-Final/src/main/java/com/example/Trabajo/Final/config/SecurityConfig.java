package com.example.Trabajo.Final.config;

import java.net.http.HttpClient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    private final CorsConfigurationSource configurationSource;
    @Autowired
    private JwtFilter jwtFilter;

    SecurityConfig(CorsConfigurationSource configurationSource) {
        this.configurationSource = configurationSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .headers(h -> h.frameOptions(f -> f.disable()))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/usuarios/registro").permitAll()
                    .requestMatchers("/api/categorias/**").permitAll()
                    .requestMatchers("/api/usuarios/**").permitAll()
                    .requestMatchers("/api/login").permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/api/entrenadores/**").permitAll()
                    .requestMatchers("/api/jugadores/**").hasAnyRole("USUARIO", "ENTRENADOR", "ADMIN")
                    .requestMatchers("/api/estadisticas/**").permitAll()
                    .requestMatchers("/api/partidos/**").permitAll()
                    .requestMatchers("/api/campeonatos/**").permitAll()
                    .requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()
                    .requestMatchers("/api/login", "/api/usuarios/registro").permitAll()
                    .anyRequest().authenticated()
                )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config  = new CorsConfiguration();
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
