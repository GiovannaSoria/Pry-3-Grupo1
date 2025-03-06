package com.espe.micro_tareas.repositories;

import com.espe.micro_tareas.models.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
