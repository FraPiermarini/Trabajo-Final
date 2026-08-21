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
                    .requestMatchers("/api/usuarios/**").hasAnyRole("ADMIN")
                    .requestMatchers("/api/login").permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/entrenadores/**").hasAnyRole("USUARIO", "ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/entrenadores/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/entrenadores/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/entrenadores/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/jugadores/**").hasAnyRole("USUARIO", "ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/jugadores/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/jugadores/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/api/jugadores/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/jugadores/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/categorias/**").hasAnyRole("USUARIO", "ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/categorias/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/categorias/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/categorias/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/estadisticas/**").hasAnyRole("USUARIO","ENTRENADOR","ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/estadisticas/**").hasAnyRole("ENTRENADOR","ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/estadisticas/**").hasAnyRole("ENTRENADOR","ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/partidos/**").hasAnyRole("USUARIO", "ENTRENADOR","ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/partidos/**").hasAnyRole("ENTRENADOR","ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/partidos/**").hasAnyRole("ENTRENADOR","ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/api/partidos/**").hasAnyRole("ENTRENADOR","ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/partidos/**").hasAnyRole("ENTRENADOR","ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/campeonatos/**").hasAnyRole("USUARIO","ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/campeonatos/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/campeonatos/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/campeonatos/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/incidencias/**").hasAnyRole("USUARIO", "ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/incidencias/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/incidencias/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/api/incidencias/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/incidencias/**").hasAnyRole("ENTRENADOR", "ADMIN")
                    .requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()
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
