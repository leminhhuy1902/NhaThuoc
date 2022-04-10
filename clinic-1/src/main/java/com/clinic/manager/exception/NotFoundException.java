package com.clinic.manager.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

/**
 * Exception raised when a requested resource was not found.
 */
public class NotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor takes a not-found entity's ID.
     * @param id
     */
    public NotFoundException(Long id) {
        super(null, "Not found", Status.NOT_FOUND, String.format("'%s' not found", id));
    }
}