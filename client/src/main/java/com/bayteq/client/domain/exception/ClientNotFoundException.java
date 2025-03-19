package com.bayteq.client.domain.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Cliente con id " + id + " no encontrado");
    }
}
