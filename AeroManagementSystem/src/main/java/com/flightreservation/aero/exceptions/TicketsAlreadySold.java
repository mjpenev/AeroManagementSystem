package com.flightreservation.aero.exceptions;

public class TicketsAlreadySold extends RuntimeException {

    public TicketsAlreadySold() {
        super();
    }

    public TicketsAlreadySold(Throwable exception) {
        super(exception);
    }

    public TicketsAlreadySold(String message) {
        super(message);
    }

    public TicketsAlreadySold(String message, Throwable exception) {
        super(message, exception);
    }

}
