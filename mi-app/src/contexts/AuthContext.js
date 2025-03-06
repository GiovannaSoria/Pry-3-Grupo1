import React, { createContext, useState, useEffect, useContext } from 'react';
import { jwtDecode } from 'jwt-decode'; // Importar jwtDecode
import { login, logout, getAuthToken } from '../services/authService';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [authToken, setAuthToken] = useState(getAuthToken());
    const [roles, setRoles] = useState([]);

    useEffect(() => {
        if (authToken) {
            localStorage.setItem('authToken', authToken);
            const decodedToken = jwtDecode(authToken);
            setRoles(decodedToken.roles);
        } else {
            localStorage.removeItem('authToken');
            setRoles([]);
        }
    }, [authToken]);

    const handleLogin = async (username, password) => {
        const data = await login(username, password);
        setAuthToken(data.token);
        setRoles(data.roles);
    };

    const handleLogout = () => {
        logout();
        setAuthToken(null);
        setRoles([]);
    };

    return (
        <AuthContext.Provider value={{ authToken, roles, handleLogin, handleLogout }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    return useContext(AuthContext);
};
