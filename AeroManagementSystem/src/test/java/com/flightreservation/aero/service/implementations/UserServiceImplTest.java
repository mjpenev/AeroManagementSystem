package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.dto.requests.RegisterRequest;
import com.flightreservation.aero.enums.Role;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.repository.UserRepository;
import com.flightreservation.aero.service.interfaces.UserService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Test
    void testCreateUserImpl() {
        UserRepository userRepoMock = mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepoMock);

        RegisterRequest request = new RegisterRequest();
        request.setUsername("David");
        request.setFirstName("David");
        request.setLastName("Beckham");
        request.setRole(Role.USER);
        request.setEmail("davidbeckham@gmail.com");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());

        when(userRepoMock.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(userRepoMock.save(any(User.class)))
                .thenAnswer(i -> i.getArgument(0));

        User result = userService.createUserImpl(request);

        assertEquals(user.getUserId() , result.getUserId());
        assertEquals(user.getRole(), result.getRole());
        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getLastName(), result.getLastName());
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    void testGetUserById() {
        UserRepository userRepoMock = mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepoMock);

        User user = new User();
        user.setUserId(35L);


        when(userRepoMock.findById(35L)).thenReturn(Optional.of(user));
        when(userRepoMock.getReferenceById(35L)).thenReturn(user);

        User result = userService.getUserById(35L);

        assertEquals(35L, result.getUserId());
    }

    @Test
    void testGetUserByUsername() {
        UserRepository userRepoMock = mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepoMock);

        User user = new User();
        user.setUsername("CleverPerson");


        when(userRepoMock.findByUsername("CleverPerson")).thenReturn(Optional.of(user));

        User result = userService.getUserByUsername("CleverPerson");

        assertEquals("CleverPerson", result.getUsername());
    }

    @Test
    void testUpdateUser() {

    }

    @Test
    void testGetAllUsers() {

    }

    @Test
    void testUserExists() {

    }
}
