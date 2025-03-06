package com.espe.micro_tareas.config;

import com.espe.micro_tareas.filters.JwtRequestFilter;
import com.espe.micro_tareas.clients.AuthClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final AuthClient authClient;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter, AuthClient authClient) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.authClient = authClient;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desactivar CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/tareas").hasAnyRole("ADMIN", "LIDER")
                        .requestMatchers(HttpMethod.GET, "/api/tareas/**").hasAnyRole("ADMIN", "LIDER", "EMPLEADO")
                        .requestMatchers(HttpMethod.PUT, "/api/tareas/**").hasAnyRole("ADMIN", "LIDER")
                        .requestMatchers(HttpMethod.DELETE, "/api/tareas/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No se gestionan sesiones, solo JWT
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Agregar el filtro para JWT

        return http.build();
    }
}
