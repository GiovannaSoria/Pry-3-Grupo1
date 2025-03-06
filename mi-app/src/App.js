import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, useAuth } from './contexts/AuthContext';
import Login from './components/Auth/Login';
import Dashboard from './components/Dashboard/Dashboard';
import CreateProject from './components/projects/CreateProject';
import CreateTask from './components/tasks/CreateTask';
import ProjectList from './components/projects/ProjectList';
import TaskList from './components/tasks/TaskList';
import Navbar from './components/common/Navbar';
import './App.css';

const AppContent = () => {
    const { authToken } = useAuth();

    return (
        <>
            {authToken && <Navbar />}
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/create-project" element={<CreateProject />} />
                <Route path="/create-task" element={<CreateTask />} />
                <Route path="/projects" element={<ProjectList />} />
                <Route path="/tasks" element={<TaskList />} />
                <Route path="*" element={<Navigate to="/login" />} /> {/* Redirige a /login por defecto */}
            </Routes>
        </>
    );
};

function App() {
    return (
        <AuthProvider>
            <Router>
                <AppContent />
            </Router>
        </AuthProvider>
    );
}

export default App;