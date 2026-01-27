package com.flightreservation.aero.service.interfaces;

import com.flightreservation.aero.dto.requests.RegisterRequest;
import com.flightreservation.aero.dto.requests.UpdateUserRequest;
import com.flightreservation.aero.exceptions.UserNotFoundException;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.Ticket;
import com.flightreservation.aero.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    /* General user operations */
    User createUser(RegisterRequest request);
    User getUserById(Long userId);
    User getUserByUsername(String username);
    User updateUser(Long userId, UpdateUserRequest request);
    List<User> getAllUsers();
    boolean userExists(Long userId);
    boolean userExists(String username);
    Ticket buyTicket(Long userId, Long flightId, int SeatNum);

    /* Admin user operations */
    Flight createFlight(Flight flight);
    Flight updateFlightById(Long flightId);
    void cancelFlight(Long flightId);
    void delayFlight(Long flightId);
}
