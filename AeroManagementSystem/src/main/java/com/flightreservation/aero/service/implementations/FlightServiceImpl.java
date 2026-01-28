package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.dto.requests.FlightCreationRequest;
import com.flightreservation.aero.dto.requests.FlightUpdateRequest;
import com.flightreservation.aero.enums.FlightStatus;
import com.flightreservation.aero.exceptions.FlightDoesNotExist;
import com.flightreservation.aero.exceptions.TicketsAlreadySold;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.Ticket;
import com.flightreservation.aero.repository.FlightRepository;
import com.flightreservation.aero.service.interfaces.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    @Override
    @Transactional
    public Flight createFlight(FlightCreationRequest request) {
        if (request.getArrivalTime().isBefore(request.getDepartureTime())) {
            throw new IllegalArgumentException("Arrival time must be after departure time.");
        }

        Flight flight = new Flight();
        flight.setFlightClass(request.getFlightClass());
        flight.setFlightStatus(FlightStatus.SCHEDULED);
        flight.setPrice(request.getPrice());
        flight.setSeats(request.getSeats());
        flight.setTickets(new ArrayList<>());
        flight.setFromDestination(request.getFromDestination());
        flight.setToDestination(request.getToDestination());
        flight.setArrivalTime(request.getArrivalTime());
        flight.setDepartureTime(request.getDepartureTime());

       return flightRepository.save(flight);
    }

    @Override
    @Transactional
    public void updateFlightById(Long flightId, FlightUpdateRequest request) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightDoesNotExist("Flight with given id was not found in database."));

        flight.setPrice(request.getPrice());
        flight.setDepartureTime(request.getDepartureTime());
        flight.setArrivalTime(request.getArrivalTime());
        flight.setFlightStatus(request.getFlightStatus());
    }

    @Override
    @Transactional
    public void deleteFlight(Long flightId) {
        if (!flightExists(flightId)) {
            throw new FlightDoesNotExist("Flight with given Id does not exist");
        }
        if (!flightRepository.findById(flightId).get().getTickets().isEmpty()) {
            throw new TicketsAlreadySold("Flight with already sold tickets couldn't be cancelled.");
        }

        flightRepository.deleteById(flightId);
    }

    @Override
    @Transactional
    public void cancelFlight(Long flightId) {
        if (!flightExists(flightId)) {
            throw new FlightDoesNotExist("Flight with given Id does not exist");
        }

        flightRepository.findById(flightId).get().setFlightStatus(FlightStatus.CANCELLED);
    }

    @Override
    @Transactional
    public void delayFlight(Long flightId) {
        if (!flightExists(flightId)) {
            throw new FlightDoesNotExist("Flight with given Id does not exist");
        }

        flightRepository.findById(flightId).get().setFlightStatus(FlightStatus.DELAYED);
    }

    @Override
    public Flight getFlightById(Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightDoesNotExist(
                        "Flight with the given id does not exist"
                ));
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> getFlightsByDestination(String destination) {
        return flightRepository.findAll().stream()
                .filter(flight -> flight.getFromDestination().equals(destination) || flight.getToDestination().equals(destination))
                .toList();
    }

    @Override
    public List<Flight> getFlightsByDate(LocalDateTime fromDate, LocalDateTime toDate) {
        return flightRepository.findAll().stream()
                .filter(flight -> flight.getDepartureTime().isBefore(toDate) && flight.getDepartureTime().isAfter(fromDate))
                .toList();
    }

    @Override
    public List<Ticket> getAllTicketsForFlight(Long flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        if (flight.isEmpty()) {
            throw new FlightDoesNotExist("Flight with the given id does not exist in database.");
        }

        return flight.get().getTickets();
    }

    @Override
    public boolean flightExists(Long flightId) {
        return flightRepository.existsById(flightId);
    }
}
