package com.flightreservation.aero.service.interfaces;

import com.flightreservation.aero.dto.requests.FlightCreationRequest;
import com.flightreservation.aero.dto.requests.FlightUpdateRequest;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    Flight createFlight(FlightCreationRequest request);
    void updateFlightById(Long flightId, FlightUpdateRequest request);
    public void deleteFlight(Long flightId);
    void cancelFlight(Long flightId);
    void delayFlight(Long flightId);
    Flight getFlightById(Long flightId);
    List<Flight> getAllFlights();
    List<Flight> getFlightsByDestination(String destination);
    List<Flight> getFlightsByDate(LocalDateTime fromDate, LocalDateTime toDate);
    List<Ticket> getAllTicketsForFlight(Long flightId);
    boolean flightExists(Long flightId);
    boolean isFlightFull(Long flightId);
}
