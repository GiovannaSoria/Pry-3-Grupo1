import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Dashboard = () => {
    const [projects, setProjects] = useState([]);
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        const fetchProjectsAndTasks = async () => {
            const token = localStorage.getItem('authToken');
            const config = {
                headers: { Authorization: `Bearer ${token}` }
            };

            try {
                const projectsResponse = await axios.get('http://localhost:8002/api/proyectos', config);
                setProjects(projectsResponse.data);

                const tasksResponse = await axios.get('http://localhost:8001/api/tareas', config);
                setTasks(tasksResponse.data);
            } catch (error) {
                console.error('Error fetching projects and tasks', error);
            }
        };

        fetchProjectsAndTasks();
    }, []);

    return (
        <div style={{ padding: '20px' }}>
            <h1>Bienvenido al Dashboard</h1>
            <p>En este panel, puedes ver y gestionar todos tus proyectos y tareas.</p>
            <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                <div style={{ width: '45%', border: '1px solid #ccc', borderRadius: '8px', padding: '10px', boxShadow: '0 0 10px rgba(0,0,0,0.1)' }}>
                    <h2>Proyectos ({projects.length})</h2>

                    <ul>
                        {projects.map(project => (
                            <li key={project.id}>{project.nombre}</li>
                        ))}
                    </ul>
                </div>
                <div style={{ width: '45%', border: '1px solid #ccc', borderRadius: '8px', padding: '10px', boxShadow: '0 0 10px rgba(0,0,0,0.1)' }}>
                    <h2>Tareas ({tasks.length})</h2>
                    <ul>
                        {tasks.map(task => (
                            <li key={task.id}>{task.nombre}</li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>

    );
};

export default Dashboard;
