/*
 * @(#)UserService.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.services;

import br.com.mrms.mytasks.model.Role;
import br.com.mrms.mytasks.model.User;
import br.com.mrms.mytasks.repository.RoleRepository;
import br.com.mrms.mytasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 01/02/2021
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public List<User> getUsersAll() {
        return userRepository.findAll();
    }

    public User salveUser(User user) {
        Set<Role> roles = getRoles(user);
        user.setRoles(roles);
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        if (!userRepository.existsById(id))
            throw new EntityNotFoundException();

        user.setId(id);

        return salveUser(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    private Set<Role> getRoles(User user) {
        Set<Role> rolesBanco = user.getRoles()
                .stream()
                .map(role -> roleRepository.findByName(role.getName()))
                .collect(Collectors.toSet());

        return rolesBanco;
    }
}
