package com.flightreservation.aero.exceptions;

public class TicketDoesNotExist extends RuntimeException {

    public TicketDoesNotExist() {
        super();
    }

    public TicketDoesNotExist(Throwable exception) {
        super(exception);
    }

    public TicketDoesNotExist(String message) {
        super(message);
    }

    public TicketDoesNotExist(String message, Throwable exception) {
        super(message, exception);
    }

}
