package com.flightreservation.aero.service.interfaces;

import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    Optional<Flight> getFlightById(Long flightId);
    List<Flight> getAllFlights();
    List<Flight> getFlightsByDestination(String destination);
    List<Flight> getFlightsByDate(LocalDateTime fromDate, LocalDateTime toDate);
    List<User> getAllUsersForFlight(Long flightId);
    boolean flightExists(Long flightId);
}
