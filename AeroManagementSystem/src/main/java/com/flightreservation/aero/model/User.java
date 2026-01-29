package com.flightreservation.aero.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flightreservation.aero.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    private Role role;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnore
    private List<Ticket> tickets;
}
