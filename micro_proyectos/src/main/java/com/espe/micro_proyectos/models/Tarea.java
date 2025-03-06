package com.espe.micro_proyectos.models;

import java.util.Date;

public class Tarea {
    private Long id;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaLimite;
    private EstadoTarea estado;
    private Long usuarioAsignado;

    public enum EstadoTarea {
        PENDIENTE,
        EN_PROGRESO,
        COMPLETADA
    }

    public Tarea() {}

    public Tarea(Long id, String nombre, String descripcion, Date fechaCreacion, Date fechaLimite, EstadoTarea estado, Long usuarioAsignado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.usuarioAsignado = usuarioAsignado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Date getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(Date fechaLimite) { this.fechaLimite = fechaLimite; }

    public EstadoTarea getEstado() { return estado; }
    public void setEstado(EstadoTarea estado) { this.estado = estado; }

    public Long getUsuarioAsignado() { return usuarioAsignado; }
    public void setUsuarioAsignado(Long usuarioAsignado) { this.usuarioAsignado = usuarioAsignado; }
}
