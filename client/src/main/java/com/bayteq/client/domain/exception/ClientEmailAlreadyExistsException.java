package com.bayteq.client.domain.exception;

public class ClientEmailAlreadyExistsException extends RuntimeException {
    public ClientEmailAlreadyExistsException(String email) {
        super("Ya existe un cliente con el email: " + email);
    }
}
