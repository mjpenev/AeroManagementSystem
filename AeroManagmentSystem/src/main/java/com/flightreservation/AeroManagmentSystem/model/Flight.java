package com.flightreservation.AeroManagmentSystem.model;

import com.flightreservation.AeroManagmentSystem.enums.Direction;
import com.flightreservation.AeroManagmentSystem.enums.FlightClass;
import com.flightreservation.AeroManagmentSystem.enums.FlightStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Flight {
    private Long flightId;
    private int seats;
    private Direction direction;
    private String fromDestination;
    private String toDestination;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private FlightClass flightClass;
    private FlightStatus flightStatus;
    private List<User> passengers;
}
