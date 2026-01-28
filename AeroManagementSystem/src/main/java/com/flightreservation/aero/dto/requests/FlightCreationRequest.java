package com.flightreservation.aero.dto.requests;

import com.flightreservation.aero.enums.FlightClass;
import com.flightreservation.aero.enums.FlightStatus;
import com.flightreservation.aero.model.Ticket;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FlightCreationRequest {
    private int seats;
    private int price;
    private String fromDestination;
    private String toDestination;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private FlightClass flightClass;
    private FlightStatus flightStatus;
    private List<Ticket> tickets;
}
