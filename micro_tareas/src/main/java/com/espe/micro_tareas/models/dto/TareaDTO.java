package com.espe.micro_tareas.models.dto;

import java.util.Date;

public class TareaDTO {
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaLimite;
    private String estado;
    private Long proyectoId;
    private Long asignadoAId;

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Long getAsignadoAId() {
        return asignadoAId;
    }

    public void setAsignadoAId(Long asignadoAId) {
        this.asignadoAId = asignadoAId;
    }
}
