/*
 * @(#)UpDataToBase.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.config;

import br.com.mrms.mytasks.model.*;
import br.com.mrms.mytasks.repository.RoleRepository;
import br.com.mrms.mytasks.repository.TaskCategoryRepository;
import br.com.mrms.mytasks.repository.TaskRepository;
import br.com.mrms.mytasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 01/02/2021
 */
@Configuration
@Profile("dev")
public class UpDataToBase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Bean
    CommandLineRunner execute() {
        return args -> {

            Role roleAdmin = new Role(ERole.ROLE_ADMIN);
            roleAdmin = roleRepository.save(roleAdmin);

            Role roleUser = new Role(ERole.ROLE_USER);
            roleUser = roleRepository.save(roleUser);

            User user = new User();
            user.setUsername("Admin");
            String senhaCrypt = BCrypt.hashpw("123456", BCrypt.gensalt());
            user.setPassword(senhaCrypt); //BeCrypt
            user.setRoles(Set.of(roleAdmin, roleUser));

            userRepository.save(user);

            TaskCategory taskCategory = new TaskCategory();
            taskCategory.setName("Estudos");

            taskCategoryRepository.save(taskCategory);

            Task task = new Task();
            task.setDescription("Aprender Spring Boot");
            task.setDataDelivery(LocalDate.now().plusDays(1));
            task.setStatus(TaskStatus.OPEN);
            task.setVisible(true);
            task.setCategory(taskCategory);
            task.setUser(user);

            taskRepository.save(task);

            Task task2 = new Task();
            task2.setDescription("Aprender Spring Data");
            task2.setDataDelivery(LocalDate.now().plusDays(1));
            task2.setStatus(TaskStatus.OPEN);
            task2.setVisible(true);
            task2.setCategory(taskCategory);
            task2.setUser(user);

            taskRepository.save(task2);

            Task task3 = new Task();
            task3.setDescription("Ler Clean Code");
            task3.setDataDelivery(LocalDate.now().plusDays(1));
            task3.setStatus(TaskStatus.OPEN);
            task3.setVisible(true);
            task3.setCategory(taskCategory);
            task3.setUser(user);

            taskRepository.save(task3);

        };
    }
}
