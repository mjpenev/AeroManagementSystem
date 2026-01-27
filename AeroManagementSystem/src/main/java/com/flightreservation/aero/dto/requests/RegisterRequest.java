package com.flightreservation.aero.dto.requests;

import com.flightreservation.aero.enums.Role;
import com.flightreservation.aero.model.Ticket;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
