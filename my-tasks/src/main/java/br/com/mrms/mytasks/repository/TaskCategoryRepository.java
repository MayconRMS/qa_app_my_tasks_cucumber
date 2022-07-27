/*
 * @(#)TaskCategoryRepository.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.repository;

import br.com.mrms.mytasks.model.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 01/02/2021
 */
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Integer> {
}
