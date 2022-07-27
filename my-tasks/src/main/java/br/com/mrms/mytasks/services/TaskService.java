/*
 * @(#)TaskService.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.services;

import br.com.mrms.mytasks.exception.TaskStatusException;
import br.com.mrms.mytasks.model.Task;
import br.com.mrms.mytasks.model.TaskStatus;
import br.com.mrms.mytasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 01/02/2021
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksPorDescription(String description) {
        return taskRepository.findByDescriptionLike("%" + description + "%");
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Task salveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Integer id, Task task) {
        if (!taskRepository.existsById(id))
            throw new EntityNotFoundException();
        task.setId(id);
        return taskRepository.save(task);
    }

    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }

    public Task startTaskById(Integer id) {
        Task task = getTaskById(id);

        if (!TaskStatus.OPEN.equals(task.getStatus()))
            throw new TaskStatusException("Não é possível iniciar a task com status diferente de aberto"
                    + task.getStatus().name());

        task.setStatus(TaskStatus.IN_PROCESS);

        return salveTask(task);
    }

    public Task finishTaskById(Integer id) {
        Task task = getTaskById(id);

        if (TaskStatus.CANCELED.equals(task.getStatus()))
            throw new TaskStatusException("Não é possível concluir uma task cancelada");

        task.setStatus(TaskStatus.FINISH);

        return salveTask(task);
    }

    public Task canceledTaskById(Integer id) {
        Task task = getTaskById(id);

        if (TaskStatus.FINISH.equals(task.getStatus()))
            throw new TaskStatusException("Não é possível cancelar uma task concluída");

        task.setStatus(TaskStatus.CANCELED);
        return salveTask(task);
    }


}
