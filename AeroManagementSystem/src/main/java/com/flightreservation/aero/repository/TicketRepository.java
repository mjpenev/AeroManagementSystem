package com.flightreservation.aero.repository;

import com.flightreservation.aero.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
