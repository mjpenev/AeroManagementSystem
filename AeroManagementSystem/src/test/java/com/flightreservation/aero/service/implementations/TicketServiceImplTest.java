package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.enums.Direction;
import com.flightreservation.aero.exceptions.SeatAlreadyReserved;
import com.flightreservation.aero.exceptions.TicketDoesNotExist;
import com.flightreservation.aero.exceptions.TicketsAlreadySold;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.Ticket;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.repository.TicketRepository;
import com.flightreservation.aero.service.interfaces.FlightService;
import com.flightreservation.aero.service.interfaces.TicketService;
import com.flightreservation.aero.service.interfaces.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TicketServiceImplTest {
    @Test
    void testCreateTicket() {
        TicketRepository ticketRepoMock = mock(TicketRepository.class);
        FlightService flightServiceMock = mock(FlightService.class);
        UserService userServiceMock = mock(UserService.class);

        TicketService ticketService = new TicketServiceImpl(ticketRepoMock, flightServiceMock, userServiceMock);
        Flight flight = new Flight();
        Long flightId = 1L;
        flight.setFlightId(flightId);
        User user = new User();
        Long userId = 1L;
        user.setUserId(1L);

        when(flightServiceMock.getFlightById(flightId)).thenReturn(flight);
        when(userServiceMock.getUserById(userId)).thenReturn(user);
        when(ticketRepoMock.existsByFlightAndSeatNumber(flight, 25)).thenReturn(false);

        when(ticketRepoMock.save(any(Ticket.class)))
                .thenAnswer(i -> i.getArgument(0));

        Ticket result = ticketService.createTicket(userId, flightId, Direction.ONEWAY_TICKET, 25);

        assertEquals(user, result.getPassenger());
        assertEquals(flight, result.getFlight());
        assertEquals(25, result.getSeatNumber());
        assertEquals(Direction.ONEWAY_TICKET, result.getDirection());
    }

    @Test
    void testCreateTicketOnFullFlight() {
        TicketRepository ticketRepoMock = mock(TicketRepository.class);
        UserService userService = mock(UserService.class);
        FlightService flightService = mock(FlightService.class);

        TicketService ticketService = new TicketServiceImpl(ticketRepoMock, flightService, userService);

        Flight flight = new Flight();
        flight.setFlightId(1L);

        when(flightService.getFlightById(1L)).thenReturn(flight);
        when(ticketRepoMock.existsByFlightAndSeatNumber(flight, 25)).thenReturn(false);
        when(flightService.isFlightFull(anyLong())).thenReturn(true);

        assertThrows(TicketsAlreadySold.class, () -> ticketService.createTicket(1L, 1L, Direction.ONEWAY_TICKET, 25));
    }

    @Test
    void testCreateTicketSeatTaken() {
        TicketRepository ticketRepoMock = mock(TicketRepository.class);
        UserService userService = mock(UserService.class);
        FlightService flightService = mock(FlightService.class);

        TicketService ticketService = new TicketServiceImpl(ticketRepoMock, flightService, userService);

        Flight flight = new Flight();
        flight.setFlightId(1L);

        when(flightService.getFlightById(1L)).thenReturn(flight);
        when(ticketRepoMock.existsByFlightAndSeatNumber(flight, 25)).thenReturn(true);

        assertThrows(SeatAlreadyReserved.class, () -> ticketService.createTicket(1L, 1L, Direction.ONEWAY_TICKET, 25));
    }

    @Test
    void testRemoveTicket() {
        TicketRepository ticketRepoMock = mock(TicketRepository.class);
        UserService userService = mock(UserService.class);
        FlightService flightService = mock(FlightService.class);

        TicketService ticketService = new TicketServiceImpl(ticketRepoMock, flightService, userService);

        Ticket ticket = new Ticket();
        ticket.setTicketId(3L);


        when(ticketRepoMock.findById(3L)).thenReturn(Optional.of(ticket));

        ticketService.removeTicket(3L);

        verify(ticketRepoMock, times(1)).findById(3L);
        verify(ticketRepoMock, times(1)).deleteById(3L);
    }

    @Test
    void testRemoveTicketNotExisting() {
        TicketRepository ticketRepoMock = mock(TicketRepository.class);
        UserService userService = mock(UserService.class);
        FlightService flightService = mock(FlightService.class);

        TicketService ticketService = new TicketServiceImpl(ticketRepoMock, flightService, userService);

        when(ticketRepoMock.findById(3L)).thenReturn(Optional.empty());

        assertThrows(TicketDoesNotExist.class, () -> ticketService.removeTicket(3L));
    }

    @Test
    void testGetAllTicketsForUserId() {
        TicketRepository ticketRepoMock = mock(TicketRepository.class);
        UserService userService = mock(UserService.class);
        FlightService flightService = mock(FlightService.class);

        TicketService ticketService = new TicketServiceImpl(ticketRepoMock, flightService, userService);

        User user = new User();
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        Ticket ticket3 = new Ticket();

        user.setTickets(List.of(ticket1, ticket2, ticket3));

        when(userService.getUserById(1L)).thenReturn(user);

        List<Ticket> result = ticketService.getAllTicketsForUserId(1L);

        assertEquals(List.of(ticket1, ticket2, ticket3), result);
    }
}