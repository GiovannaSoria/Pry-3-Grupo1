import axios from 'axios';
import { jwtDecode } from 'jwt-decode';

const API_URL = 'http://localhost:8003/api/usuarios/login'; // URL del servidor de autenticación

export const login = async (username, password) => {
    try {
        const response = await axios.post(API_URL, {
            user: username,
            password
        });
        const token = response.data.token;
        localStorage.setItem('authToken', token);
        const decodedToken = jwtDecode(token);
        return { token, roles: decodedToken.roles };
    } catch (error) {
        console.error('Error al iniciar sesión', error);
        throw error;
    }
};

export const getAuthToken = () => {
    return localStorage.getItem('authToken');
};

export const logout = () => {
    localStorage.removeItem('authToken');
};
