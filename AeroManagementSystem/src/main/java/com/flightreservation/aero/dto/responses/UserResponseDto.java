package com.flightreservation.aero.dto.responses;

import com.flightreservation.aero.enums.Role;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private Long userId;
    private String username;
    private String email;
    private Role role;
}
