package com.flightreservation.AeroManagmentSystem.model;

import java.time.LocalDateTime;

public class Ticket {
    private Long ticketId;
    private String seatNumber;
    private LocalDateTime purchaseDate;
    private User passenger;
    private Flight flight;
}
