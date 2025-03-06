package com.espe.micro_tareas.services;

import com.espe.micro_tareas.models.Proyecto;
import com.espe.micro_tareas.models.Usuarios;
import com.espe.micro_tareas.models.entities.Tarea;
import com.espe.micro_tareas.repositories.ProyectoRepository;
import com.espe.micro_tareas.repositories.TareaRepository;
import com.espe.micro_tareas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuariosRepository;

@Override
public Tarea save(Tarea tarea) {
    // Verificar si el proyecto existe y obtener sus datos completos
    Optional<Proyecto> proyectoOpt = proyectoRepository.findById(tarea.getProyecto().getId());
    if (!proyectoOpt.isPresent()) {
        throw new RuntimeException("Proyecto no encontrado");
    }
    tarea.setProyecto(proyectoOpt.get());

    // Verificar si el usuario existe y obtener sus datos completos
    Optional<Usuarios> usuarioOpt = usuariosRepository.findById(tarea.getAsignadoA().getId());
    if (!usuarioOpt.isPresent()) {
        throw new RuntimeException("Usuario no encontrado");
    }
    tarea.setAsignadoA(usuarioOpt.get());

    return tareaRepository.save(tarea);
}

    @Override
    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }

    @Override
    public Optional<Tarea> findById(Long id) {
        return tareaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        tareaRepository.deleteById(id);
    }
}