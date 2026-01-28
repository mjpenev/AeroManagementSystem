package com.flightreservation.aero.service.interfaces;

import com.flightreservation.aero.dto.requests.RegisterRequest;
import com.flightreservation.aero.dto.requests.UpdateUserRequest;
import com.flightreservation.aero.model.Ticket;
import com.flightreservation.aero.model.User;

import java.util.List;

public interface UserService {
    User createUserImpl(RegisterRequest request);
    User getUserById(Long userId);
    User getUserByUsername(String username);
    User updateUser(Long userId, UpdateUserRequest request);
    List<User> getAllUsers();
    boolean userExists(Long userId);
    Ticket buyTicket(Long userId, Long flightId, int SeatNum);
}
