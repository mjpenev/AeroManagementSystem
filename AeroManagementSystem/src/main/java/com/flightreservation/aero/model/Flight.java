package com.flightreservation.aero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flightreservation.aero.enums.Direction;
import com.flightreservation.aero.enums.FlightClass;
import com.flightreservation.aero.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long flightId;

    private int seats;
    private int price;

    private String fromDestination;
    private String toDestination;

    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;

    private FlightClass flightClass;
    private FlightStatus flightStatus;

    @OneToMany(mappedBy = "flight")
    @JsonIgnore
    private List<Ticket> tickets;
}
