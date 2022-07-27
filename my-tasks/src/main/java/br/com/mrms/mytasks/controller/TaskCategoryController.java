/*
 * @(#)TaskCategoryController.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.controller;

import br.com.mrms.mytasks.controller.response.TaskCategoryResponse;
import br.com.mrms.mytasks.model.TaskCategory;
import br.com.mrms.mytasks.services.TaskCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 01/02/2021
 */
@RestController
@RequestMapping("/category")
public class TaskCategoryController {

    @Autowired
    private TaskCategoryService taskCategoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<TaskCategoryResponse> getAllCategories() {
        List<TaskCategory> categories = taskCategoryService.getAllCategories();
        return categories.stream().map(category -> modelMapper.map(category, TaskCategoryResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TaskCategoryResponse getCategoryById(@PathVariable Integer id) {
        return modelMapper.map(taskCategoryService.getCategoryById(id), TaskCategoryResponse.class);
    }

    @PostMapping
    public TaskCategoryResponse saveCategory(@Valid @RequestBody TaskCategory taskCategory) {
        return modelMapper.map(taskCategoryService.save(taskCategory), TaskCategoryResponse.class);
    }

    @PutMapping("/{id}")
    public TaskCategoryResponse updateTask(@PathVariable Integer id, @Valid @RequestBody TaskCategory taskCategory) {
        return modelMapper.map(taskCategoryService.update(id, taskCategory), TaskCategoryResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCategoryById(@PathVariable Integer id) {
        taskCategoryService.removeById(id);
    }
}
