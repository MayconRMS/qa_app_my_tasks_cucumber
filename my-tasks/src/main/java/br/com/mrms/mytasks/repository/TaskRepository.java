/*
 * @(#)TaskRepository.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.repository;

import br.com.mrms.mytasks.model.Task;
import br.com.mrms.mytasks.model.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 01/02/2021
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {

    public List<Task> findByDescription(String description);

    public List<Task> findByDescriptionLike(String description);

    public List<Task> findByCategory(TaskCategory category);

    @Query("select t from Task t inner join t.category c where c.name = ?1")
    public List<Task> findByNameCategory(String categoryName);

}
