package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.exceptions.SeatAlreadyReserved;
import com.flightreservation.aero.exceptions.TicketDoesNotExist;
import com.flightreservation.aero.exceptions.TicketsAlreadySold;
import com.flightreservation.aero.exceptions.UserNotFoundException;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.Ticket;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.repository.TicketRepository;
import com.flightreservation.aero.service.interfaces.FlightService;
import com.flightreservation.aero.service.interfaces.TicketService;
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


    @Override
    @Transactional
    public Ticket createTicket(User user, Flight flight, int seatNum) {
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

        flight.getTickets().add(ticket);

        return ticketRepository.save(ticket);
    }

    @Override
    public void removeTicket(Long ticketId) {
        throw new TicketDoesNotExist();
    }

    @Override
    public List<Ticket> getAllTicketsForUserId(Long userId) {
        throw new UserNotFoundException();
    }
}
