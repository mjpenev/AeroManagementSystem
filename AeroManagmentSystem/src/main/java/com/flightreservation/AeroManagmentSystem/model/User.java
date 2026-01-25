package com.flightreservation.AeroManagmentSystem.model;
import com.flightreservation.AeroManagmentSystem.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class User implements UserDetails {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private List<Flight> flights;
    private Long userId;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


}
