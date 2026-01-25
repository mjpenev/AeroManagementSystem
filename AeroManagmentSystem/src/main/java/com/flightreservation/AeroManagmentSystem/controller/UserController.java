package com.flightreservation.AeroManagmentSystem.controller;

import com.flightreservation.AeroManagmentSystem.dto.responses.Response;
import com.flightreservation.AeroManagmentSystem.dto.responses.UserResponseDto;
import com.flightreservation.AeroManagmentSystem.model.User;
import com.flightreservation.AeroManagmentSystem.service.interfaces.UserService;
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
    public ResponseEntity<Response> getUserById(@PathVariable("id") Long userId) {
        User user = userService.getUserById(userId);

        UserResponseDto userResponseDto = new UserResponseDto(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .data("user", userResponseDto)
                .message("User retreived succesfully.")
                .success(true)
                .build()
        );
    }
}
