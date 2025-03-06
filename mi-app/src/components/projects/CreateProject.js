import React, { useState } from 'react';
import axios from 'axios';

const CreateProject = () => {
    const [nombre, setNombre] = useState('');
    const [descripcion, setDescripcion] = useState('');
    const [fechaInicio, setFechaInicio] = useState('');
    const [fechaFin, setFechaFin] = useState('');
    const [liderId, setLiderId] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const projectData = {
            nombre,
            descripcion,
            fechaInicio,
            fechaFin,
            liderId: { id: liderId }
        };

        try {
            const response = await axios.post('http://localhost:8002/api/proyectos', projectData, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('authToken')}`
                }
            });
            console.log('Proyecto creado con éxito:', response.data);
            // Redirigir o mostrar mensaje de éxito
        } catch (error) {
            console.error('Error al crear el proyecto:', error);
        }
    };

    return (
        <div>
            <h1>Crear Proyecto</h1>
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
                    Fecha Inicio:
                    <input
                        type="datetime-local"
                        value={fechaInicio}
                        onChange={(e) => setFechaInicio(e.target.value)}
                    />
                </label>
                <br />
                <label>
                    Fecha Fin:
                    <input
                        type="datetime-local"
                        value={fechaFin}
                        onChange={(e) => setFechaFin(e.target.value)}
                    />
                </label>
                <br />
                <label>
                    Líder ID:
                    <input
                        type="number"
                        value={liderId}
                        onChange={(e) => setLiderId(e.target.value)}
                    />
                </label>
                <br />
                <button type="submit">Crear Proyecto</button>
            </form>
        </div>
    );
};

export default CreateProject;
