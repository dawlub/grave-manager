package com.dlubera.grave.manager.service.exception;

public class GraveManagerServiceException extends RuntimeException {

    public GraveManagerServiceException(String message) {
        super(message);
    }

    public GraveManagerServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
