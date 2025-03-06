import React, { useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode'; // Corregir el nombre de la librería
import { AuthContext } from '../../contexts/AuthContext';

const Navbar = () => {
    const { roles, handleLogout, authToken } = useContext(AuthContext);
    const decodedToken = authToken ? jwtDecode(authToken) : null;
    const username = decodedToken ? decodedToken.sub : 'Usuario';
    const navigate = useNavigate();

    const onLogout = () => {
        handleLogout();
        navigate('/login'); // Redirigir a la página de login
    };

    return (
        <nav>
            <ul>
                <li><Link to="/dashboard">Dashboard</Link></li>
                
                {/* Mostrar Proyectos y Tareas solo para ADMIN y LIDER */}
                {(roles.includes('ROLE_ADMIN') || roles.includes('ROLE_LIDER')) && (
                    <>
                        <li><Link to="/projects">Proyectos</Link></li>
                        <li><Link to="/tasks">Tareas</Link></li>
                        <li><Link to="/create-project">Crear Proyecto</Link></li>
                        <li><Link to="/create-task">Crear Tarea</Link></li>
                    </>
                )}

                {/* Solo mostrar Tareas para EMPLEADO */}
                {roles.includes('ROLE_EMPLEADO') && (
                    <li><Link to="/tasks">Tareas</Link></li>
                )}

                <li><button onClick={onLogout}>Logout</button></li>
                <li>Hola, {username}</li>
            </ul>
        </nav>
    );
};

export default Navbar;
