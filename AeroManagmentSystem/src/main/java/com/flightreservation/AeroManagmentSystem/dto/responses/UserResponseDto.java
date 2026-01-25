package com.flightreservation.AeroManagmentSystem.dto.responses;

import com.flightreservation.AeroManagmentSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long userId;
    private String username;
    private String email;
    private Role role;
}
