package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.exceptions.SeatAlreadyReserved;
import com.flightreservation.aero.exceptions.TicketDoesNotExist;
import com.flightreservation.aero.exceptions.UserAlreadyExists;
import com.flightreservation.aero.exceptions.UserNotFoundException;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.Ticket;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.repository.TicketRepository;
import com.flightreservation.aero.service.interfaces.TicketService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(User user, Flight flight, int seatNum) {
        if (ticketRepository.existsByFlightAndSeatNumber(flight, seatNum)) {
            throw new SeatAlreadyReserved();
        }

        Ticket ticket = new Ticket();
        ticket.setPassenger(user);
        ticket.setFlight(flight);
        ticket.setSeatNumber(seatNum);
        ticket.setPurchaseDate(LocalDateTime.now());

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
