package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.dto.requests.LoginRequest;
import com.flightreservation.aero.dto.requests.RegisterRequest;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.service.interfaces.AuthenticationService;
import com.flightreservation.aero.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;

    @Override
    public User register(RegisterRequest request) {
        return userService.createUserImpl(request);
    }

    @Override
    public User login(LoginRequest request) {
        return null;
    }

}
