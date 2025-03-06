// src/components/tasks/TaskDetails.jsx
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const TaskDetails = () => {
  const { taskId } = useParams();
  const [task, setTask] = useState(null);

  useEffect(() => {
    const fetchTaskDetails = async () => {
      try {
        const response = await axios.get(`http://localhost:8000/api/tareas/${taskId}`);
        setTask(response.data);
      } catch (error) {
        console.error('Error fetching task details:', error);
      }
    };

    fetchTaskDetails();
  }, [taskId]);

  if (!task) return <div>Cargando tarea...</div>;

  return (
    <div>
      <h2>Detalles de la Tarea</h2>
      <p><strong>Nombre:</strong> {task.name}</p>
      <p><strong>Descripción:</strong> {task.description}</p>
      <p><strong>Estado:</strong> {task.status}</p>
      {/* Agrega más campos según tu estructura de datos */}
    </div>
  );
};

export default TaskDetails;
