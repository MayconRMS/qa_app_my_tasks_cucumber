/*
 * @(#)TaskStatusException.java 1.0 01/02/2021
 * Copyright (c) 2021, All rights reserved.
 * Use is subject to license terms.
 */

package br.com.mrms.mytasks.exception;

/**
 * @author Maycon Ricardo Monteiro de Siqueira
 * @version 1.0 01/02/2021
 */
public class TaskStatusException extends RuntimeException {

    private static final long serialVersionUID = 5481721327806548261L;

    public TaskStatusException() {
        super();
    }

    public TaskStatusException(String message) {
        super(message);
    }

    public TaskStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskStatusException(Throwable cause) {
        super(cause);
    }

    public TaskStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
