package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.enums.Direction;
import com.flightreservation.aero.exceptions.*;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.Ticket;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.repository.TicketRepository;
import com.flightreservation.aero.service.interfaces.FlightService;
import com.flightreservation.aero.service.interfaces.TicketService;
import com.flightreservation.aero.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final FlightService flightService;
    private final UserService userService;


    @Override
    @Transactional
    public Ticket createTicket(Long userId, Long flightId, Direction direction, int seatNum) {
        Flight flight = flightService.getFlightById(flightId);
        User user = userService.getUserById(userId);

        if (ticketRepository.existsByFlightAndSeatNumber(flight, seatNum)) {
            throw new SeatAlreadyReserved();
        }
        if (flightService.isFlightFull(flight.getFlightId())) {
            throw new TicketsAlreadySold("All tickets for this flight are already sold.");
        }

        Ticket ticket = new Ticket();
        ticket.setPassenger(user);
        ticket.setFlight(flight);
        ticket.setSeatNumber(seatNum);
        ticket.setPurchaseDate(LocalDateTime.now());
        ticket.setDirection(direction);

        return ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public void removeTicket(Long ticketId) {
        if (ticketRepository.findById(ticketId).isEmpty()) {
            throw new TicketDoesNotExist("Ticket with given Id does not exist");
        }

        ticketRepository.deleteById(ticketId);
    }

    @Override
    @Transactional
    public List<Ticket> getAllTicketsForUserId(Long userId) {
        return userService.getUserById(userId).getTickets();
    }
}
