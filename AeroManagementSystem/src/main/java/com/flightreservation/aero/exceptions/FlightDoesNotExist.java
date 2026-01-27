package com.flightreservation.aero.exceptions;

public class FlightDoesNotExist extends RuntimeException {

    public FlightDoesNotExist() {
        super();
    }

    public FlightDoesNotExist(Throwable exception) {
        super(exception);
    }

    public FlightDoesNotExist(String message) {
        super(message);
    }

    public FlightDoesNotExist(String message, Throwable exception) {
        super(message, exception);
    }

}
