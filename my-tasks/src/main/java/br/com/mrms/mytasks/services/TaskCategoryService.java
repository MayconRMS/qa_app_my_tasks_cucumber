/*
 * @(#)TaskCategoryService.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.services;

import br.com.mrms.mytasks.model.TaskCategory;
import br.com.mrms.mytasks.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 01/02/2021
 */
@Service
public class TaskCategoryService {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    public List<TaskCategory> getAllCategories() {
        return taskCategoryRepository.findAll();
    }

    public TaskCategory getCategoryById(Integer id) {
        return taskCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public TaskCategory save(TaskCategory taskCategory) {
        return taskCategoryRepository.save(taskCategory);
    }

    public TaskCategory update(Integer id, TaskCategory taskCategory) {
        if (!taskCategoryRepository.existsById(id))
            throw new EntityNotFoundException();
        taskCategory.setId(id);
        return taskCategoryRepository.save(taskCategory);
    }

    public void removeById(Integer id) {
        taskCategoryRepository.deleteById(id);
    }


}
