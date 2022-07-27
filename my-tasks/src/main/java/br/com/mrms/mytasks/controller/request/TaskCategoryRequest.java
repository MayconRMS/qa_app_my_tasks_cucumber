/*
 * @(#)TaskCategoryResponse.java 1.0 03/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 03/02/2021
 */
@Getter
@Setter
public class TaskCategoryRequest {

    private Integer id;

    @NotBlank(message = "Nome da categoria não pode estar vazio")
    @Size(min = 5, max = 150, message = "Nome da categoria deve ter 5 a 150 caracteres")
    private String nome;

}
