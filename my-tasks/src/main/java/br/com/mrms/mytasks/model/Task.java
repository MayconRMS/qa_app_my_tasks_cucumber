/*
 * @(#)Task.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 01/02/2021
 */
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "description_task", nullable = false, length = 150)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.OPEN;

    private LocalDate dataDelivery;
    private boolean visible = true;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TaskCategory category;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
}
