package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.dto.requests.RegisterRequest;
import com.flightreservation.aero.dto.requests.UpdateUserRequest;
import com.flightreservation.aero.exceptions.FlightDoesNotExist;
import com.flightreservation.aero.exceptions.UserAlreadyExists;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.Ticket;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.repository.UserRepository;
import com.flightreservation.aero.service.interfaces.FlightService;
import com.flightreservation.aero.service.interfaces.TicketService;
import com.flightreservation.aero.service.interfaces.UserService;
import com.flightreservation.aero.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(RegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExists("The user is already registered in database");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User updateUser(Long userId, UpdateUserRequest request) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean userExists(Long userId) {
        return false;
    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public Ticket buyTicket(Long userId, Long flightId, int SeatNum) {
        return null;
    }

    @Override
    public Flight createFlight(Flight flight) {
        return null;
    }

    @Override
    public Flight updateFlightById(Long flightId) {
        return null;
    }

    @Override
    public void cancelFlight(Long flightId) {

    }

    @Override
    public void delayFlight(Long flightId) {

    }
}
