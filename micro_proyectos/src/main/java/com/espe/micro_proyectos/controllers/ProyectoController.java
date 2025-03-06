package com.espe.micro_proyectos.controllers;

import com.espe.micro_proyectos.models.entities.Proyecto;
import com.espe.micro_proyectos.services.ProyectoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    // Listar todos los proyectos
    @GetMapping
    public List<Proyecto> listarProyectos() {
        return proyectoService.findAll();
    }

    // Obtener proyecto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> obtenerProyecto(@PathVariable Long id) {
        Optional<Proyecto> proyecto = proyectoService.findById(id);
        return proyecto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva
    @PostMapping
    public ResponseEntity<?> crearProyecto(@Valid @RequestBody Proyecto proyecto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(validarErrores(result));
        }
        return ResponseEntity.ok(proyectoService.save(proyecto));
    }

    private Map<String, String> validarErrores(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
        return errores;
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizarProyecto(@PathVariable Long id, @RequestBody Proyecto proyecto) {
        if (!proyectoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        proyecto.setId(id);
        return ResponseEntity.ok(proyectoService.save(proyecto));
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Long id) {
        if (!proyectoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        proyectoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}