package com.espe.micro_proyectos.services;

import com.espe.micro_proyectos.models.entities.Proyecto;

import java.util.List;
import java.util.Optional;

public interface ProyectoService {
    List<Proyecto> findAll(); // Obtener todas las tareas
    Optional<Proyecto> findById(Long id); // Obtener tarea por ID
    Proyecto save(Proyecto tarea); // Guardar tarea
    void deleteById(Long id); // Eliminar tarea
}
