package com.flightreservation.AeroManagmentSystem.service.interfaces;

import com.flightreservation.AeroManagmentSystem.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getUserById(Long userId);
    User getUserByUsername(String username);

    User updateUser(User user);

    List<User> getAllUsers();

    boolean userExists(Long userId);
    boolean userExists(String username);
}
