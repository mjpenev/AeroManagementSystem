package com.flightreservation.aero.exceptions;

public class SeatAlreadyReserved extends RuntimeException {

    public SeatAlreadyReserved() {
        super();
    }

    public SeatAlreadyReserved(Throwable exception) {
        super(exception);
    }

    public SeatAlreadyReserved(String message) {
        super(message);
    }

    public SeatAlreadyReserved(String message, Throwable exception) {
        super(message, exception);
    }

}
