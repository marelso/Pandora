package com.pandora.backend.exception;

import lombok.Getter;

import static java.lang.String.format;

@Getter
public class ServiceException extends RuntimeException {
    public ServiceException(String message, Object... arguments) {
        super(format(message, arguments));
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
    }
}
