package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.service.interfaces.FlightService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {
    @Override
    public Flight createFlight(Flight flight) {
        return null;
    }

    @Override
    public Flight updateFlightById(Long flightId) {
        return null;
    }

    @Override
    public void cancelFlight(Long flightId) {

    }

    @Override
    public void delayFlight(Long flightId) {

    }

    @Override
    public Optional<Flight> getFlightById(Long flightId) {
        return Optional.empty();
    }

    @Override
    public List<Flight> getAllFlights() {
        return List.of();
    }

    @Override
    public List<Flight> getFlightsByDestination(String destination) {
        return List.of();
    }

    @Override
    public List<Flight> getFlightsByDate(LocalDateTime fromDate, LocalDateTime toDate) {
        return List.of();
    }

    @Override
    public List<User> getAllUsersForFlight(Long flightId) {
        return List.of();
    }

    @Override
    public boolean flightExists(Long flightId) {
        return false;
    }
}
