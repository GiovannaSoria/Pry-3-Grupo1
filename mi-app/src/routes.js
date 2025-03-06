// src/routes.jsx
import React from 'react';
import { Routes, Route } from 'react-router-dom';
import ProjectList from './components/projects/ProjectList';
import ProjectDetails from './components/projects/ProjectDetails';
import ProjectForm from './components/projects/ProjectForm';
import TaskList from './components/tasks/TaskList';
import TaskDetails from './components/tasks/TaskDetails';
import TaskForm from './components/tasks/TaskForm';
import PrivateRoute from './components/PrivateRoute';

const RoutesConfig = () => {
  return (
    <Routes>
      {/* Rutas para proyectos */}
      <Route path="/projects" element={<PrivateRoute roles={['ADMIN', 'LIDER', 'EMPLEADO']} component={ProjectList} />} />
      <Route path="/projects/create" element={<PrivateRoute roles={['ADMIN', 'LIDER']} component={ProjectForm} />} />
      <Route path="/projects/edit/:projectId" element={<PrivateRoute roles={['ADMIN', 'LIDER']} component={ProjectForm} />} />
      <Route path="/projects/:projectId" element={<PrivateRoute roles={['ADMIN', 'LIDER', 'EMPLEADO']} component={ProjectDetails} />} />

      {/* Rutas para tareas */}
      <Route path="/tasks" element={<PrivateRoute roles={['ADMIN', 'LIDER', 'EMPLEADO']} component={TaskList} />} />
      <Route path="/tasks/create" element={<PrivateRoute roles={['ADMIN', 'LIDER']} component={TaskForm} />} />
      <Route path="/tasks/edit/:taskId" element={<PrivateRoute roles={['ADMIN', 'LIDER']} component={TaskForm} />} />
      <Route path="/tasks/:taskId" element={<PrivateRoute roles={['ADMIN', 'LIDER', 'EMPLEADO']} component={TaskDetails} />} />
    </Routes>
  );
};

export default RoutesConfig;
