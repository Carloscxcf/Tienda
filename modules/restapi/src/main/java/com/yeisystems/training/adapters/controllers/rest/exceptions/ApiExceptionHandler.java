package com.yeisystems.training.adapters.controllers.rest.exceptions;

import com.yeisystems.training.adapters.controllers.rest.dto.HandleErrorResponse;
import com.yeisystems.training.usecases.exceptions.NoAcceptableException;
import com.yeisystems.training.usecases.exceptions.RequestConflictException;
import com.yeisystems.training.usecases.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Representa status 412 Resource Not Found
     **/
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(final ResourceNotFoundException exception, final HttpServletRequest request) {

        HandleErrorResponse error = new HandleErrorResponse();
        error.setStatus(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(exception.getMessage());
        error.setCode(exception.getError());
        log.info("Error _: " + error.getCode() + " Message _:" + error.getMessage() + " Request Uri _:" + request.getRequestURI() + " Request Method _:" + request.getMethod());

        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(error);
    }

    /**
     * Representa status 400 Resource Bad Request
     **/
    @ResponseBody
    @ExceptionHandler({RequestBadException.class})
    public ResponseEntity<?> handleResourceBadRequest(final RequestBadException exception, final HttpServletRequest request) {

        HandleErrorResponse error = new HandleErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setCode(exception.getError());
        log.info("Error _: " + error.getCode() + " Message _:" + error.getMessage() + " Request Uri _:" + request.getRequestURI() + " Request Method _:" + request.getMethod());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Representa status 406 Resource No Acceptable
     **/
    @ResponseBody
    @ExceptionHandler({NoAcceptableException.class})
    public ResponseEntity<?> handleResourceNoAcceptable(final NoAcceptableException exception, final HttpServletRequest request) {

        HandleErrorResponse error = new HandleErrorResponse();
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setMessage(exception.getMessage());
        error.setCode(exception.getError());
        log.info("Error _: " + error.getCode() + " Message _:" + error.getMessage() + " Request Uri _:" + request.getRequestURI() + " Request Method _:" + request.getMethod());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    /**
     * Representa status 409 Resource Conflict (Un recurso existente)
     **/
    @ResponseBody
    @ExceptionHandler({RequestConflictException.class})
    public ResponseEntity<?> handleResourceConflict(final RequestConflictException exception, final HttpServletRequest request) {

        HandleErrorResponse error = new HandleErrorResponse();
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(exception.getMessage());
        error.setCode(exception.getError());
        log.info("Error _: " + error.getCode() + " Message _:" + error.getMessage() + " Request Uri _:" + request.getRequestURI() + " Request Method _:" + request.getMethod());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /**
     * Representa status 500 Internal Server Error (Todos los Errores no controlados)
     **/
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptionErrorInternal(final Exception exception, final HttpServletRequest request) {

        Map<String, String> responseError = new HashMap<>();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);

        responseError.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value() + " " + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        responseError.put("message", exception.getMessage() != null ? exception.getMessage() : "Internal Server error unknown");
        responseError.put("error", "MT:UNEXPECTED_EXCEPTION:GET:500:01");
        responseError.put("uri", request.getRequestURI());

        exception.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    }
}
