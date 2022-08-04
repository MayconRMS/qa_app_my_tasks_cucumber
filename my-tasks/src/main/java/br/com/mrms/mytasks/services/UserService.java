/*
 * @(#)UserService.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.services;

import br.com.mrms.mytasks.controller.response.JwtResponse;
import br.com.mrms.mytasks.model.ERole;
import br.com.mrms.mytasks.model.Role;
import br.com.mrms.mytasks.model.User;
import br.com.mrms.mytasks.repository.RoleRepository;
import br.com.mrms.mytasks.repository.UserRepository;
import br.com.mrms.mytasks.security.UserDetailsImpl;
import br.com.mrms.mytasks.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
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

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public List<User> getUsersAll() {
        return userRepository.findAll();
    }

    public User salveUser(User user) {
        Set<Role> roles = getRoles(user);
        user.setRoles(roles);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
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
                .map(role -> roleRepository.findByName(role.getName()).orElse(null))
                .collect(Collectors.toSet());

        return rolesBanco;
    }

    public JwtResponse authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl)
                authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
    }

    public void registerUser(String username, String password, String confirmPassword, Set<String> strRoles) {
        if (userRepository.existsByUsername(username)) {
            throw new EntityExistsException("Usuário já existe");
        }
        User user = new User(username, passwordEncoder.encode(password));
        Set<Role> roles = new HashSet<Role>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new EntityNotFoundException("Role não encontrada"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new EntityNotFoundException("Role não encontrada"));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new EntityNotFoundException("Role não encontrada"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
    }
}
