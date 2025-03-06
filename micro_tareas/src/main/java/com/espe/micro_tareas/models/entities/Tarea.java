package com.espe.micro_tareas.models.entities;

import com.espe.micro_tareas.models.Proyecto;
import com.espe.micro_tareas.models.Usuarios;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la tarea no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Column(name = "descripcion")
    private String descripcion;

    @PastOrPresent(message = "La fecha de creación no puede ser en el futuro")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Future(message = "La fecha límite debe estar en el futuro")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_limite")
    private Date fechaLimite;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado de la tarea es obligatorio")
    @Column(name = "estado")
    private EstadoTarea estado;

    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    @NotNull(message = "El proyecto asociado es obligatorio")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "asignado_a", nullable = false)
    @NotNull(message = "Debe asignarse un usuario a la tarea")
    private Usuarios asignadoA;

    @PrePersist
    public void prePersist() {
        if (fechaCreacion == null) {
            fechaCreacion = new Date();
        }
    }

    // Getters y Setters

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

    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }

    public Usuarios getAsignadoA() { return asignadoA; }
    public void setAsignadoA(Usuarios asignadoA) { this.asignadoA = asignadoA; }

    // Enum para el estado de la tarea
    public enum EstadoTarea {
        PENDIENTE,
        EN_PROGRESO,
        COMPLETADA
    }
}
