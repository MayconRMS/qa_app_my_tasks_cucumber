/*
 * @(#)TaskResponse.java 1.0 03/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 03/02/2021
 */
@Getter
@Setter
public class TaskRequest {

    private Integer id;

    @NotBlank(message = "Descrição da tarefa não pode estar vazio")
    @Size(min = 5, max = 150, message = "Descrição da tarefa deve ter 5 a 150 caracteres")
    private String description;

    @FutureOrPresent(message = "Data de entrega deve ser futura")
    private LocalDate dataDelivery;

    @NotNull(message = "Necessário informar uma categoria para a tarefa")
    @Min(value = 1, message = "Categoria inválida")
    private Integer categoryId;

    @NotNull(message = "Necessário informar um usuário para a tarefa")
    @Min(value = 1, message = "Usuário inválido")
    private Integer userId;

}
