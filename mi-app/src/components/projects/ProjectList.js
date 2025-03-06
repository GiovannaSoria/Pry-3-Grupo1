import React, { useEffect, useState, useContext } from 'react';
import axios from 'axios';
import { AuthContext } from '../../contexts/AuthContext';
import '../../styles/ProjectList.css';

const ProjectList = () => {
    const [projects, setProjects] = useState([]);
    const { authToken } = useContext(AuthContext);

    useEffect(() => {
        const fetchProjects = async () => {
            const config = {
                headers: { Authorization: `Bearer ${authToken}` }
            };

            try {
                const response = await axios.get('http://localhost:8002/api/proyectos', config);
                setProjects(response.data);
            } catch (error) {
                console.error('Error fetching projects', error);
            }
        };

        fetchProjects();
    }, [authToken]);

    return (
        <div>
            <h1>Proyectos</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Fecha de Inicio</th>
                        <th>Fecha de Fin</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {projects.map(project => (
                        <tr key={project.id}>
                            <td>{project.id}</td>
                            <td>{project.nombre}</td>
                            <td>{project.descripcion}</td>
                            <td>{project.fechaInicio}</td>
                            <td>{project.fechaFin}</td>
                            <td>
                                <button>Editar</button>
                                <button>Eliminar</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ProjectList;
