package com.devsuperior.dsdesafios.dsdesafio1mod3.exceptions.services;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
