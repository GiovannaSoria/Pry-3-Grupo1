import React, { useContext } from 'react';
import { Route, Navigate } from 'react-router-dom';
import { AuthContext } from '../contexts/AuthContext';

const ProtectedRoute = ({ element, requiredRoles, ...rest }) => {
    const { roles, isAuthenticated } = useContext(AuthContext);

    if (!isAuthenticated) {
        return <Navigate to="/login" />;
    }

    const hasPermission = requiredRoles.some(role => roles.includes(role));

    if (!hasPermission) {
        return <Navigate to="/dashboard" />;
    }

    return <Route {...rest} element={element} />;
};

export default ProtectedRoute;
