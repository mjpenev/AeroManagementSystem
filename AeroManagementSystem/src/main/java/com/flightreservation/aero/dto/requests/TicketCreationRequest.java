package com.flightreservation.aero.dto.requests;

import com.flightreservation.aero.enums.Direction;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketCreationRequest {
    private int seatNumber;
    private Direction direction;
    private Long passengerId;
    private Long flightId;
}
