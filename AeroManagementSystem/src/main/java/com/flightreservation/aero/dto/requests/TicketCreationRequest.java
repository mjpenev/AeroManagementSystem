package com.flightreservation.aero.dto.requests;

import lombok.Data;

@Data
public class TicketCreationRequest {
    private Long flightId;
    private String seatNumber;
}
