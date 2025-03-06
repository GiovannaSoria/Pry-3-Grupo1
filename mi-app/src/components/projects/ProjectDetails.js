// src/components/projects/ProjectDetails.jsx
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const ProjectDetails = () => {
  const { projectId } = useParams();
  const [project, setProject] = useState(null);

  useEffect(() => {
    const fetchProjectDetails = async () => {
      try {
        const response = await axios.get(`http://localhost:8000/api/projects/${projectId}`);
        setProject(response.data);
      } catch (error) {
        console.error('Error fetching project details:', error);
      }
    };

    fetchProjectDetails();
  }, [projectId]);

  if (!project) return <div>Cargando proyecto...</div>;

  return (
    <div>
      <h2>Detalles del Proyecto</h2>
      <p><strong>Nombre:</strong> {project.name}</p>
      <p><strong>Descripción:</strong> {project.description}</p>
      <p><strong>Estado:</strong> {project.status}</p>
      {/* Puedes agregar más detalles del proyecto aquí */}
    </div>
  );
};

export default ProjectDetails;
