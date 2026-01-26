package com.flightreservation.aero.service.interfaces;

import com.flightreservation.aero.dto.requests.LoginRequest;
import com.flightreservation.aero.dto.requests.RegisterRequest;
import com.flightreservation.aero.model.User;

public interface AuthenticationService {
    User register(RegisterRequest request);
    User login(LoginRequest request);
}
