package com.flightreservation.aero.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserRequest {
    private String username;
    private String email;
}