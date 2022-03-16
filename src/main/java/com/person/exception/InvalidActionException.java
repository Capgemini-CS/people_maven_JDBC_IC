package com.person.exception;

public class InvalidActionException extends Exception {
    public InvalidActionException(String errorMessage) {
        super(errorMessage);
    }
}
