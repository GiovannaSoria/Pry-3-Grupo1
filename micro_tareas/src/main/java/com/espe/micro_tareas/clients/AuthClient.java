package com.espe.micro_tareas.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "micro-usuarios", url = "http://localhost:8003/api/usuarios")
public interface AuthClient {
    @GetMapping("/auth/validate-token")
    ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token);
}
