package com.espe.micro_tareas.controllers;

import com.espe.micro_tareas.models.entities.Tarea;
import com.espe.micro_tareas.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    // Listar todas las tareas
    @GetMapping
    public List<Tarea> listarTareas() {
        return tareaService.findAll();
    }

    // Obtener tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerTarea(@PathVariable Long id) {
        Optional<Tarea> tarea = tareaService.findById(id);
        return tarea.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva tarea
    @PostMapping
    public ResponseEntity<?> crearTarea(@Valid @RequestBody Tarea tarea, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(validarErrores(result));
        }
        return ResponseEntity.ok(tareaService.save(tarea));
    }

    private Map<String, String> validarErrores(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
        return errores;
    }

    // Actualizar tarea
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        if (!tareaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tarea.setId(id);
        return ResponseEntity.ok(tareaService.save(tarea));
    }

    // Eliminar tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        if (!tareaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tareaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
