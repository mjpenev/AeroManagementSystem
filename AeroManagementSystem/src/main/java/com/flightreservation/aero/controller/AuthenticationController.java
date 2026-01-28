package com.flightreservation.aero.controller;

import com.flightreservation.aero.dto.requests.RegisterRequest;
import com.flightreservation.aero.dto.responses.Response;
import com.flightreservation.aero.exceptions.UserAlreadyExists;
import com.flightreservation.aero.service.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/api/users/register")
    public ResponseEntity<Response> createUser(@RequestBody RegisterRequest request) {
        try {
            authenticationService.register(request);

            return ResponseEntity.ok(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .success(true)
                    .message("User successfully registered.")
                    .status(HttpStatus.CREATED)
                    .build()
            );
        } catch (UserAlreadyExists excpetion) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .success(false)
                    .message("User already exists in database.")
                    .build()
            );
        }
    }
}
