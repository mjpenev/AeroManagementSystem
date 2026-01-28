package com.flightreservation.aero.dto.requests;

import com.flightreservation.aero.enums.Direction;
import lombok.Data;

@Data
public class TicketCreationRequest {
    private Long flightId;
    private String seatNumber;
    private Direction direction;
}
