package com.yeisystems.training.adapters.controllers.rest.restcontroller.generic;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.yeisystems.training.adapters.controllers.rest.constants.GlobalConstants;
import com.yeisystems.training.adapters.controllers.rest.exceptions.RequestBadException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class GenericRestController implements GlobalConstants {

    protected <T> T validate(T t, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> format("El campo '%s' %s", error.getField(), error.getDefaultMessage()))
                    .toList();

            throw new RequestBadException("Error in the fields to the body of the request.", errors.toString());
        }

        return t;
    }

    protected ResponseEntity<?> internalServerError(String message, String error) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("error", error);
        return ResponseEntity.internalServerError().body(response);
    }
}
