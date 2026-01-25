package com.flightreservation.AeroManagmentSystem.service.interfaces;

import com.flightreservation.AeroManagmentSystem.dto.requests.LoginRequest;
import com.flightreservation.AeroManagmentSystem.dto.requests.RegisterRequest;
import com.flightreservation.AeroManagmentSystem.model.User;

public interface AuthenticationService {
    User register(RegisterRequest request);
    User login(LoginRequest request);
}
