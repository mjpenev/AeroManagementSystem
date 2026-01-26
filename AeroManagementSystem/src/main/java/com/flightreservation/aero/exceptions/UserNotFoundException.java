package com.flightreservation.aero.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(Throwable exception) {
        super(exception);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable exception) {
        super (message, exception);
    }

}
