package com.espe.micro_proyectos.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        System.out.println("🔍 JwtRequestFilter: Interceptando solicitud -> " + request.getRequestURI());

        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            System.out.println("🚨 No se encontró un token en la solicitud.");
            chain.doFilter(request, response);
            return;
        }

        String jwt = authorizationHeader.substring(7);
        System.out.println(" Token recibido: " + jwt);

        String username;
        Claims claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey("secreto_super_seguro") // Asegúrate de que esta clave coincida con la de MicroUsuarios
                    .parseClaimsJws(jwt)
                    .getBody();
            username = claims.getSubject();
        } catch (Exception e) {
            System.out.println("Error al validar el token: " + e.getMessage());
            chain.doFilter(request, response);
            return;
        }

        Object rolesObject = claims.get("roles");
        List<String> roles = rolesObject != null ? (List<String>) rolesObject : new ArrayList<>();
        System.out.println("Roles extraídos del token: " + roles);

        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }
}
