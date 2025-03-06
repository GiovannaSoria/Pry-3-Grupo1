import React, { useEffect, useState, useContext } from 'react';
import axios from 'axios';
import { AuthContext } from '../../contexts/AuthContext';

const TaskList = () => {
    const [tasks, setTasks] = useState([]);
    const { authToken } = useContext(AuthContext);

    useEffect(() => {
        const fetchTasks = async () => {
            const config = {
                headers: { Authorization: `Bearer ${authToken}` }
            };

            try {
                const response = await axios.get('http://localhost:8001/api/tareas', config);
                setTasks(response.data);
            } catch (error) {
                console.error('Error fetching tasks', error);
            }
        };

        fetchTasks();
    }, [authToken]);

    return (
        <div>
            <h1>Tareas</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Fecha Límite</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {tasks.map(task => (
                        <tr key={task.id}>
                            <td>{task.id}</td>
                            <td>{task.nombre}</td>
                            <td>{task.descripcion}</td>
                            <td>{task.fechaLimite}</td>
                            <td>{task.estado}</td>
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

export default TaskList;
