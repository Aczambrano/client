package com.bayteq.client.domain.exception;

public class ClientDocumentAlreadyExistsException extends RuntimeException {
    public ClientDocumentAlreadyExistsException(String documentNumber) {
        super("Ya existe un cliente con el número de documento: " + documentNumber);
    }
}
