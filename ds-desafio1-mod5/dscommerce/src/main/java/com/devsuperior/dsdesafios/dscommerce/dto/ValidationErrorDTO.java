package com.devsuperior.dsdesafios.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends CustomErrorDTO {

    private final List<FieldMessageDTO> errors = new ArrayList<>();


    public ValidationErrorDTO(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessageDTO> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.removeIf(error -> error.getFieldName().equals(fieldName));
        errors.add(new FieldMessageDTO(fieldName, message));
    }

}
