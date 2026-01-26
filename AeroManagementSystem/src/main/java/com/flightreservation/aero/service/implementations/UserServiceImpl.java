package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.dto.requests.UpdateUserRequest;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.repository.UserRepository;
import com.flightreservation.aero.service.interfaces.UserService;
import com.flightreservation.aero.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User getUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("There is no user with the give userId: " + userId));
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
        return List.of();
    }

    @Override
    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
