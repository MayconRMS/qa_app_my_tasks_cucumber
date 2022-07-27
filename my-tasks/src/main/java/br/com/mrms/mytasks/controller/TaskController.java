/*
 * @(#)TaskController.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.controller;

import br.com.mrms.mytasks.controller.request.TaskRequest;
import br.com.mrms.mytasks.controller.response.TaskResponse;
import br.com.mrms.mytasks.model.Task;
import br.com.mrms.mytasks.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 01/02/2021
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<TaskResponse> allTasks(@RequestParam Map<String, String> parameters) {
        List<Task> tasks = new ArrayList<>();

        if (parameters.isEmpty()) {
            tasks = taskService.getAllTasks();
        } else {
            String description = parameters.get("description");
            tasks = taskService.getTasksPorDescription(description);
        }
        return tasks.stream().map(task -> modelMapper.map(task, TaskResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Integer id) {
        return modelMapper.map(taskService.getTaskById(id), TaskResponse.class);
    }

    @PostMapping
    public TaskResponse saveTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task task = modelMapper.map(taskRequest, Task.class);
        return modelMapper.map(taskService.salveTask(task), TaskResponse.class);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Integer id,@Valid @RequestBody TaskRequest taskRequest) {
        Task task = modelMapper.map(taskRequest,Task.class);
        return modelMapper.map(taskService.updateTask(id, task), TaskResponse.class);
    }

    @DeleteMapping("/{id}")
    public void removeTask(@PathVariable Integer id) {
        taskService.deleteById(id);
    }

    @PutMapping("/{id}/start")
    public TaskResponse startTask(@PathVariable Integer id) {
        return modelMapper.map(taskService.startTaskById(id), TaskResponse.class);
    }

    @PutMapping("/{id}/finish")
    public TaskResponse finishTask(@PathVariable Integer id) {
        return modelMapper.map(taskService.finishTaskById(id), TaskResponse.class);
    }

    @PutMapping("/{id}/cancel")
    public TaskResponse cancelTask(@PathVariable Integer id) {
        return modelMapper.map(taskService.canceledTaskById(id), TaskResponse.class);
    }

}
