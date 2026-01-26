package com.flightreservation.aero.controller;

import com.flightreservation.aero.dto.responses.Response;
import com.flightreservation.aero.dto.responses.UserResponseDto;
import com.flightreservation.aero.exceptions.UserNotFoundException;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Response> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();

        List<UserResponseDto> userDtos = allUsers.stream()
                .map(user -> new UserResponseDto(
                        user.getUserId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole()
                ))
                .toList();

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data("users", userDtos)
                        .message("All registered users retrieved.")
                        .success(true)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getUserById(@PathVariable("id") Long userId) throws UserNotFoundException {
        User user = userService.getUserById(userId);

        UserResponseDto userResponseDto = new UserResponseDto(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .data("users", userResponseDto)
                .message("User retreived succesfully.")
                .success(true)
                .build()
        );
    }

    @GetMapping("/{username}")
    public ResponseEntity<Response> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);

        UserResponseDto userResponseDto = new UserResponseDto(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .data("users", userResponseDto)
                .message("User retreived succesfully.")
                .success(true)
                .build()
        );
    }
}
