// src/components/projects/ProjectForm.jsx
import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';

const ProjectForm = () => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [status, setStatus] = useState('PENDING'); // Estado inicial
  const { projectId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (projectId) {
      const fetchProject = async () => {
        try {
          const response = await axios.get(`http://localhost:8000/api/projects/${projectId}`);
          setName(response.data.name);
          setDescription(response.data.description);
          setStatus(response.data.status);
        } catch (error) {
          console.error('Error fetching project:', error);
        }
      };

      fetchProject();
    }
  }, [projectId]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const projectData = { name, description, status };

    try {
      if (projectId) {
        // Actualizar proyecto existente
        await axios.put(`http://localhost:8000/api/projects/${projectId}`, projectData);
      } else {
        // Crear nuevo proyecto
        await axios.post('http://localhost:8000/api/projects', projectData);
      }
      navigate('/projects'); // Redirigir a la lista de proyectos
    } catch (error) {
      console.error('Error saving project:', error);
    }
  };

  return (
    <div>
      <h2>{projectId ? 'Editar' : 'Crear'} Proyecto</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nombre del Proyecto</label>
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
        <button type="submit">{projectId ? 'Actualizar' : 'Crear'} Proyecto</button>
      </form>
    </div>
  );
};

export default ProjectForm;
