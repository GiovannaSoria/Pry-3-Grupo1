import { createContext, useState, useEffect } from "react";
import { getTasks, createTask } from "../services/taskService";

export const TaskContext = createContext();

export const TaskProvider = ({ children }) => {
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        loadTasks();
    }, []);

    const loadTasks = async () => {
        const data = await getTasks();
        setTasks(data);
    };

    const addTask = async (task) => {
        const newTask = await createTask(task);
        setTasks([...tasks, newTask]);
    };

    return (
        <TaskContext.Provider value={{ tasks, addTask }}>
            {children}
        </TaskContext.Provider>
    );
};
