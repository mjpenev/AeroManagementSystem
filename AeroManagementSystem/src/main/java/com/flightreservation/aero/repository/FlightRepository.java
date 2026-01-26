package com.flightreservation.aero.repository;

import com.flightreservation.aero.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
