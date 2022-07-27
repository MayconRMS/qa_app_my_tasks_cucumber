/*
 * @(#)response.java 1.0 03/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.controller.response;

import br.com.mrms.mytasks.model.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 03/02/2021
 */
@Getter
@Setter
public class TaskResponse {

    private Integer id;
    private String description;
    private LocalDate dataDelivery;
    private Integer categoryId;
    private Integer userId;
    private TaskStatus taskStatus;

}
