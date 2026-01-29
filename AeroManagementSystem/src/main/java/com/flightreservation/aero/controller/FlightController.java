package com.flightreservation.aero.controller;

import com.flightreservation.aero.dto.requests.FlightCreationRequest;
import com.flightreservation.aero.dto.requests.FlightUpdateRequest;
import com.flightreservation.aero.dto.responses.Response;
import com.flightreservation.aero.exceptions.FlightDoesNotExist;
import com.flightreservation.aero.exceptions.TicketsAlreadySold;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.Ticket;
import com.flightreservation.aero.service.interfaces.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<Response> createFlight(@Valid @RequestBody FlightCreationRequest request) {
        try {
            flightService.createFlight(request);

            return ResponseEntity.ok(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .success(true)
                    .data("element", request)
                    .message("Flight created successfully.")
                    .build()
            );
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .success(false)
                    .message("Flight creation failed.")
                    .build()
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteFlight(@PathVariable("id") Long flightId)  {
        try {
            flightService.deleteFlight(flightId);

            return ResponseEntity.ok(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .success(true)
                    .message("Flight successfully removed from database")
                    .build()
            );

        } catch (FlightDoesNotExist | TicketsAlreadySold exception) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("Flight couldn't be removed.")
                    .success(false)
                    .build()
            );
        }
    }

    @GetMapping
    public ResponseEntity<Response> getAllFlights() {
        List<Flight> allUsers = flightService.getAllFlights();

        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .data("flights", allUsers)
                .message("All flights retrieved successfully")
                .success(true)
                .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getFlightById(@PathVariable("id") Long flightId) {
        try {
            Flight flight = flightService.getFlightById(flightId);

            return ResponseEntity.ok(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data("flight", flight)
                    .message("Flight retrieved successfully")
                    .success(true)
                    .build()
            );
        } catch (FlightDoesNotExist exception) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Flight with given id does not exist.")
                            .success(false)
                            .build()
            );
        }
    }

    @GetMapping("/period/{from}/{to}")
    public ResponseEntity<Response> getFlightsByDate(@PathVariable("from") LocalDateTime fromDate, @PathVariable("to") LocalDateTime toDate) {
            List<Flight> flights = flightService.getFlightsByDate(fromDate, toDate);

            return ResponseEntity.ok(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data("flights", flights)
                    .message("All flights retrieved from " + fromDate.toString() + " to " + toDate.toString())
                    .success(true)
                    .build()
            );
    }

    @GetMapping("/destination/{destination}")
    public ResponseEntity<Response> getFlightsByDate(@PathVariable("destination") String destination) {
        List<Flight> flights = flightService.getFlightsByDestination(destination);

        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .data("flights", flights)
                .message("All flights retrieved for destionation :" + destination)
                .success(true)
                .build()
        );
    }


    @GetMapping("/{id}/tickets")
    public ResponseEntity<Response> getAllTicketsForFlight(@PathVariable("id") Long flightId) {
        try {
            List<Ticket> tickets = flightService.getFlightById(flightId).getTickets();

            return ResponseEntity.badRequest().body(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("Tickets retrieved successfully for flight with ID " + flightId)
                    .data("tickets", tickets)
                    .success(false)
                    .build()
            );
        } catch (FlightDoesNotExist exception) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("Flight with given id couldn't be found in database.")
                    .success(false)
                    .build()
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateFlight(@PathVariable("id") Long flightId, FlightUpdateRequest request) {
        try {
            flightService.updateFlightById(flightId, request);

            return ResponseEntity.ok(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .success(true)
                    .message("Flight successfully updated")
                    .build()
            );

        } catch (FlightDoesNotExist exception) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("Flight with given id does not exist in database.")
                    .success(false)
                    .build()
            );
        }
    }

    @PatchMapping("/{id}/delay")
    public ResponseEntity<Response> delayFlight(@PathVariable("id") Long flightId) {
        try {
            flightService.delayFlight(flightId);

            return ResponseEntity.ok(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .success(true)
                    .message("Flight status was successfully set to delayed.")
                    .build()
            );
        } catch (FlightDoesNotExist exception) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .success(false)
                    .message("Flight with given id does not exist.")
                    .build()
            );
        }
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Response> cancelFLight(@PathVariable("id") Long flightId) {
        try {
            flightService.cancelFlight(flightId);

            return ResponseEntity.ok(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .success(true)
                    .message("Flight status was successfully set to cancelled.")
                    .build()
            );
        } catch (FlightDoesNotExist exception) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .success(false)
                    .message("Flight with given id does not exist.")
                    .build()
            );
        }
    }
}
