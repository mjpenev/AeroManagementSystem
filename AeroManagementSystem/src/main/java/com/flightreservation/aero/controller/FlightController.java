package com.flightreservation.aero.controller;

import com.flightreservation.aero.dto.requests.FlightCreationRequest;
import com.flightreservation.aero.dto.requests.FlightUpdateRequest;
import com.flightreservation.aero.dto.responses.Response;
import com.flightreservation.aero.exceptions.FlightDoesNotExist;
import com.flightreservation.aero.exceptions.TicketsAlreadySold;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.service.interfaces.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
}
