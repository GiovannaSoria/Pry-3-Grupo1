import { createContext, useState, useEffect } from "react";
import { getProjects, createProject } from "../services/projectService";

export const ProjectContext = createContext();

export const ProjectProvider = ({ children }) => {
    const [projects, setProjects] = useState([]);

    useEffect(() => {
        loadProjects();
    }, []);

    const loadProjects = async () => {
        const data = await getProjects();
        setProjects(data);
    };

    const addProject = async (project) => {
        const newProject = await createProject(project);
        setProjects([...projects, newProject]);
    };

    return (
        <ProjectContext.Provider value={{ projects, addProject }}>
            {children}
        </ProjectContext.Provider>
    );
};
