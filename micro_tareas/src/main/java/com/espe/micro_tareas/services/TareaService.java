package com.espe.micro_tareas.services;

import com.espe.micro_tareas.models.entities.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaService {
    List<Tarea> findAll(); // Obtener todas las tareas
    Optional<Tarea> findById(Long id); // Obtener tarea por ID
    Tarea save(Tarea tarea); // Guardar tarea
    void deleteById(Long id); // Eliminar tarea
}