/*
 * @(#)UserRepository.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.repository;

import br.com.mrms.mytasks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 01/02/2021
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String string);

    Boolean existsByUsername(String username);

}
