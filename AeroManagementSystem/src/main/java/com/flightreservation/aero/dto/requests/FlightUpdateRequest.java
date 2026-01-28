package com.flightreservation.aero.dto.requests;

import com.flightreservation.aero.enums.FlightStatus;
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
