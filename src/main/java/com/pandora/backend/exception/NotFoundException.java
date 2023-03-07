package com.pandora.backend.exception;

public class NotFoundException extends ServiceException {
    public NotFoundException(String entity, String id) {
        super("%s %s not found", entity, id);
    }

    public NotFoundException(String text) {
        super("%s", text);
    }

    public NotFoundException(String entity, Integer id) {
        super("%s %d not found", entity, id);
    }
}
