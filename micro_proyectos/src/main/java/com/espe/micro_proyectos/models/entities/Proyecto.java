package com.espe.micro_proyectos.models.entities;

import com.espe.micro_proyectos.models.Usuarios;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del proyecto no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    @PastOrPresent(message = "La fecha de inicio no puede estar en el futuro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Future(message = "La fecha de finalización debe estar en el futuro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    @ManyToOne
    @JoinColumn(name = "liderId", nullable = false)
    @NotNull(message = "Debe asignarse un usuario a la tarea")
    private Usuarios liderId;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }

    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }

    public Usuarios getLiderId() { return liderId; }
    public void setLiderId(Usuarios liderId) { this.liderId = liderId; }

}
