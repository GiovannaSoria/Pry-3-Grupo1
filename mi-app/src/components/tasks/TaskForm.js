// src/components/tasks/TaskForm.jsx
import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';

const TaskForm = () => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [status, setStatus] = useState('PENDING'); // Estado inicial
  const { taskId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (taskId) {
      const fetchTask = async () => {
        try {
          const response = await axios.get(`http://localhost:8000/api/tasks/${taskId}`);
          setName(response.data.name);
          setDescription(response.data.description);
          setStatus(response.data.status);
        } catch (error) {
          console.error('Error fetching task:', error);
        }
      };

      fetchTask();
    }
  }, [taskId]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const taskData = { name, description, status };

    try {
      if (taskId) {
        // Actualizar tarea existente
        await axios.put(`http://localhost:8000/api/tasks/${taskId}`, taskData);
      } else {
        // Crear nueva tarea
        await axios.post('http://localhost:8000/api/tasks', taskData);
      }
      navigate('/tasks'); // Redirigir a la lista de tareas
    } catch (error) {
      console.error('Error saving task:', error);
    }
  };

  return (
    <div>
      <h2>{taskId ? 'Editar' : 'Crear'} Tarea</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nombre de la Tarea</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Descripción</label>
          <textarea
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Estado</label>
          <select value={status} onChange={(e) => setStatus(e.target.value)}>
            <option value="PENDING">Pendiente</option>
            <option value="IN_PROGRESS">En progreso</option>
            <option value="COMPLETED">Completado</option>
          </select>
        </div>
        <button type="submit">{taskId ? 'Actualizar' : 'Crear'} Tarea</button>
      </form>
    </div>
  );
};

export default TaskForm;
