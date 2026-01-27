package com.flightreservation.aero.repository;

import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsByFlightAndSeatNumber(Flight flight, int seatNum);
}
