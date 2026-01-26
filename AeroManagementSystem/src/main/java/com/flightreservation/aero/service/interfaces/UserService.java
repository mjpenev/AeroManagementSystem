package com.flightreservation.aero.service.interfaces;

import com.flightreservation.aero.dto.requests.UpdateUserRequest;
import com.flightreservation.aero.exceptions.UserNotFoundException;
import com.flightreservation.aero.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getUserById(Long userId) throws UserNotFoundException;
    User getUserByUsername(String username);

    User updateUser(Long userId, UpdateUserRequest request);

    List<User> getAllUsers();

    boolean userExists(Long userId);
    boolean userExists(String username);
}
