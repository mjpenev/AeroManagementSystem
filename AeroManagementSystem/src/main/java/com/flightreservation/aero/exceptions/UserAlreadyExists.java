package com.flightreservation.aero.exceptions;

public class UserAlreadyExists extends RuntimeException {

    public UserAlreadyExists() {
        super();
    }

    public UserAlreadyExists(String message) {
        super(message);
    }

    public UserAlreadyExists(Throwable exception) {
        super(exception);
    }

    public UserAlreadyExists(String message, Throwable exception) {
        super(message, exception);
    }

}
