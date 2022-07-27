/*
 * @(#)UserController.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.controller;

import br.com.mrms.mytasks.controller.request.UserRequest;
import br.com.mrms.mytasks.controller.response.UserResponse;
import br.com.mrms.mytasks.model.User;
import br.com.mrms.mytasks.services.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Integer id) {
        return modelMapper.map(userService.getUserById(id), UserResponse.class);
    }

    @GetMapping
    public List<UserResponse> getUserAll() {
        List<User> users = userService.getUsersAll();
        return users.stream().map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public UserResponse saveUser(@Valid  @RequestBody UserRequest userRequest) {
        User user = modelMapper.map(userRequest,User.class);
        return modelMapper.map(userService.salveUser(user), UserResponse.class);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Integer id,@Valid  @RequestBody UserRequest userRequest) {
        User user = modelMapper.map(userRequest,User.class);
        return modelMapper.map(userService.updateUser(id, user), UserResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable Integer id) {
        userService.deleteById(id);
    }


}
