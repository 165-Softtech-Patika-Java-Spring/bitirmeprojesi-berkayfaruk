package com.example.softtectfinal.app.use.enums;

public enum UseErrorMessage implements BaseErrorMessage {

    CUSTOMER_ERROR_MESSAGE("Customer not found!"),
    ;

    private String message;
    UseErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
