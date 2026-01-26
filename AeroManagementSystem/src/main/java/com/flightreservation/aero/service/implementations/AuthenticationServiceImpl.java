package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.dto.requests.LoginRequest;
import com.flightreservation.aero.dto.requests.RegisterRequest;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.service.interfaces.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public User register(RegisterRequest request) {
        return null;
    }

    @Override
    public User login(LoginRequest request) {
        return null;
    }
}
