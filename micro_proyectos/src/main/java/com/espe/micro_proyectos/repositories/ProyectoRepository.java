package com.espe.micro_proyectos.repositories;

import com.espe.micro_proyectos.models.entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}
