/*
 * @(#)UserResponse.java 1.0 03/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 03/02/2021
 */
@Getter
@Setter
public class UserRequest {

    private Integer id;

    @NotBlank(message = "Campo nome do usuário não pode estar vazio")
    private String username;

    @NotBlank(message = "Campo senha do usuário não pode estar vazio")
    private String password;

    private Set<RoleRequest> roles;

}
