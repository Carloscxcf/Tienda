package com.yeisystems.training.adapters.controllers.rest.dto;

public class HandleErrorResponse {

    private int status;
    private String message;
    private String code;

    public HandleErrorResponse() {

    }

    public HandleErrorResponse(int status, String message, String error) {
        this.status = status;
        this.message = message;
        this.code = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
