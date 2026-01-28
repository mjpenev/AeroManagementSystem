package com.flightreservation.aero.dto.requests;

import com.flightreservation.aero.enums.FlightClass;
import com.flightreservation.aero.enums.FlightStatus;
import com.flightreservation.aero.model.Ticket;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FlightUpdateRequest {
    private int price;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private FlightStatus flightStatus;
}
