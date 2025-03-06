import axios from "axios";

export const getProjects = async () => {
    const response = await axios.get("/api/proyectos");
    return response.data;
};

export const createProject = async (project) => {
    const response = await axios.post("/api/proyectos", project);
    return response.data;
};
