package com.flightreservation.aero.dto.requests;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String username;
    private String email;
}