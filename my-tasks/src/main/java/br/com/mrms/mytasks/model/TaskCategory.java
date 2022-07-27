/*
 * @(#)TaskCategory.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 01/02/2021
 */
@Getter
@Setter
@Entity
@Table(name = "tasks_category")
public class TaskCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50)
    private String name;
}
