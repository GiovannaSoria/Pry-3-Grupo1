import React, { useState } from 'react';
import axios from 'axios';

const CreateTask = () => {
    const [nombre, setNombre] = useState('');
    const [descripcion, setDescripcion] = useState('');
    const [fechaLimite, setFechaLimite] = useState('');
    const [estado, setEstado] = useState('PENDIENTE');
    const [proyectoId, setProyectoId] = useState('');
    const [asignadoA, setAsignadoA] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const taskData = {
            nombre,
            descripcion,
            fechaLimite,
            estado,
            proyecto: { id: proyectoId },
            asignadoA: { id: asignadoA }
        };

        try {
            const response = await axios.post('http://localhost:8001/api/tareas', taskData, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('authToken')}`
                }
            });
            console.log('Tarea creada con éxito:', response.data);
            // Redirigir o mostrar mensaje de éxito
        } catch (error) {
            console.error('Error al crear la tarea:', error);
        }
    };

    return (
        <div>
            <h1>Crear Tarea</h1>
            <form onSubmit={handleSubmit}>
                <label>
                    Nombre:
                    <input
                        type="text"
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                    />
                </label>
                <br />
                <label>
                    Descripción:
                    <input
                        type="text"
                        value={descripcion}
                        onChange={(e) => setDescripcion(e.target.value)}
                    />
                </label>
                <br />
                <label>
                    Fecha Límite:
                    <input
                        type="datetime-local"
                        value={fechaLimite}
                        onChange={(e) => setFechaLimite(e.target.value)}
                    />
                </label>
                <br />
                <label>
                    Estado:
                    <select
                        value={estado}
                        onChange={(e) => setEstado(e.target.value)}
                    >
                        <option value="PENDIENTE">PENDIENTE</option>
                        <option value="EN_PROCESO">EN PROCESO</option>
                        <option value="COMPLETADA">COMPLETADA</option>
                    </select>
                </label>
                <br />
                <label>
                    Proyecto ID:
                    <input
                        type="number"
                        value={proyectoId}
                        onChange={(e) => setProyectoId(e.target.value)}
                    />
                </label>
                <br />
                <label>
                    Asignado a (ID de usuario):
                    <input
                        type="number"
                        value={asignadoA}
                        onChange={(e) => setAsignadoA(e.target.value)}
                    />
                </label>
                <br />
                <button type="submit">Crear Tarea</button>
            </form>
        </div>
    );
};

export default CreateTask;
