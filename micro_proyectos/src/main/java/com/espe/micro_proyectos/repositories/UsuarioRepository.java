package com.espe.micro_proyectos.repositories;

import com.espe.micro_proyectos.models.Usuarios;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuarios, Long> {
}
