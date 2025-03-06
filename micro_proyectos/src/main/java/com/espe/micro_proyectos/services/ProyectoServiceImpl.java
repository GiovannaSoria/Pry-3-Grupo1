package com.espe.micro_proyectos.services;

import com.espe.micro_proyectos.models.Usuarios;
import com.espe.micro_proyectos.models.entities.Proyecto;
import com.espe.micro_proyectos.repositories.ProyectoRepository;
import com.espe.micro_proyectos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

@Override
public Proyecto save(Proyecto proyecto) {
    Optional<Usuarios> usuarioOpt = usuarioRepository.findById(proyecto.getLiderId().getId());
    if (!usuarioOpt.isPresent()) {
        throw new RuntimeException("Usuario no encontrado");
    }
    proyecto.setLiderId(usuarioOpt.get());

    return proyectoRepository.save(proyecto);
}


    @Override
    public List<Proyecto> findAll() {
        return proyectoRepository.findAll();
    }

    @Override
    public Optional<Proyecto> findById(Long id) {
        return proyectoRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        proyectoRepository.deleteById(id);
    }
}
