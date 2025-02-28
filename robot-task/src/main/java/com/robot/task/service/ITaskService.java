package com.robot.task.service;

import com.robot.task.entities.Task;

import java.util.List;

public interface ITaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task createTask(Task task);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    List<Task> getTasksByUserId(Long userId);
    boolean isUserAssigned(Long taskId, Long userId);

}

