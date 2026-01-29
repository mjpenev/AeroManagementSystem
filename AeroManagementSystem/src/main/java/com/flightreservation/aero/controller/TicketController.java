package com.flightreservation.aero.controller;
import com.flightreservation.aero.dto.requests.TicketCreationRequest;
import com.flightreservation.aero.dto.responses.Response;
import com.flightreservation.aero.exceptions.FlightDoesNotExist;
import com.flightreservation.aero.exceptions.SeatAlreadyReserved;
import com.flightreservation.aero.exceptions.TicketDoesNotExist;
import com.flightreservation.aero.exceptions.TicketsAlreadySold;
import com.flightreservation.aero.model.Ticket;
import com.flightreservation.aero.service.interfaces.FlightService;
import com.flightreservation.aero.service.interfaces.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<Response> createTicket(@Valid @RequestBody TicketCreationRequest request) {
        try {
            ticketService.createTicket(
                    request.getPassengerId(),
                    request.getFlightId(),
                    request.getDirection(),
                    request.getSeatNumber()
            );

            return ResponseEntity.ok(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("Ticket successfully bought for flight" + request.getFlightId())
                    .success(true)
                    .build()
            );
        } catch (SeatAlreadyReserved | TicketsAlreadySold exception) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("Ticket purchase failed. Double-check provided details")
                    .success(false)
                    .build()
            );
        }
    }

}
