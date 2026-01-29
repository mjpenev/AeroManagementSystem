package com.flightreservation.aero.service.interfaces;
import com.flightreservation.aero.enums.Direction;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.Ticket;
import com.flightreservation.aero.model.User;

import java.util.List;

public interface TicketService {
    Ticket createTicket(Long userId, Long flightId, Direction direction, int seatNum);
    void removeTicket(Long ticketId);
    List<Ticket> getAllTicketsForUserId(Long userId);
}
