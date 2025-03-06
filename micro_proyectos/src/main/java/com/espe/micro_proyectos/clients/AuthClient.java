package com.espe.micro_proyectos.clients;

import com.espe.micro_proyectos.models.Usuarios;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "micro-usuarios", url = "http://localhost:8003/api/usuarios")
public interface AuthClient {
    @GetMapping("/auth/validate-token")
    ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token);

    @GetMapping("/{id}")
    Usuarios obtenerUsuarioPorId(@PathVariable Long id, @RequestHeader("Authorization") String token);
}