import axios from "axios";

export const getTasks = async () => {
    const response = await axios.get("/api/tareas");
    return response.data;
};

export const createTask = async (task) => {
    const response = await axios.post("/api/tareas", task);
    return response.data;
};
