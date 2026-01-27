package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.dto.requests.LoginRequest;
import com.flightreservation.aero.dto.requests.RegisterRequest;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.repository.UserRepository;
import com.flightreservation.aero.service.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    @Override
    public User register(RegisterRequest request) {
        return null;
    }

    @Override
    public User login(LoginRequest request) {
        return null;
    }

}
