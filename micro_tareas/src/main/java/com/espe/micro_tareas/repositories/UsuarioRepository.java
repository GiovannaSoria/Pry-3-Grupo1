package com.espe.micro_tareas.repositories;

import com.espe.micro_tareas.models.Usuarios;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuarios, Long> {
}
