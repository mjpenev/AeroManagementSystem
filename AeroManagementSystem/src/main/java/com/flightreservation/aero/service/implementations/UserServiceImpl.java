package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.dto.requests.RegisterRequest;
import com.flightreservation.aero.dto.requests.UpdateUserRequest;
import com.flightreservation.aero.exceptions.FlightDoesNotExist;
import com.flightreservation.aero.exceptions.UserAlreadyExists;
import com.flightreservation.aero.model.Ticket;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.repository.UserRepository;
import com.flightreservation.aero.service.interfaces.FlightService;
import com.flightreservation.aero.service.interfaces.UserService;
import com.flightreservation.aero.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FlightService flightService;

    @Override
    @Transactional
    public User createUserImpl(RegisterRequest request) {

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
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User does not exist in database.");
        }

        return userRepository.getReferenceById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User does not exist in database.");
        }

        return userRepository.findByUsername(username).get();
    }

    @Override
    @Transactional
    public User updateUser(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User does not exist in database."));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }

}
