/*
 * @(#)UserResponse.java 1.0 03/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.controller.response;

import br.com.mrms.mytasks.model.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 03/02/2021
 */
@Getter
@Setter
public class UserResponse {

    private Integer id;
    private String name;
    private List<TaskResponse> tasks;
}
