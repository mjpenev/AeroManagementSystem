package com.flightreservation.aero.service.implementations;

import com.flightreservation.aero.dto.requests.FlightCreationRequest;
import com.flightreservation.aero.dto.requests.FlightUpdateRequest;
import com.flightreservation.aero.enums.FlightClass;
import com.flightreservation.aero.enums.FlightStatus;
import com.flightreservation.aero.exceptions.FlightDoesNotExist;
import com.flightreservation.aero.model.Flight;
import com.flightreservation.aero.repository.FlightRepository;
import com.flightreservation.aero.service.interfaces.FlightService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightServiceImplTest {
    @Test
    void testCreateFlightHappyPath() {
        FlightRepository flightRepoMock = mock(FlightRepository.class);
        FlightService flightService = new FlightServiceImpl(flightRepoMock);

        FlightCreationRequest request = new FlightCreationRequest();
        request.setFlightStatus(FlightStatus.SCHEDULED);
        request.setFlightClass(FlightClass.ECONOMY);
        request.setPrice(200);
        request.setSeats(150);
        request.setToDestination("SOFIA");
        request.setFromDestination("LONDON");
        request.setArrivalTime(LocalDateTime.of(2026, 3, 3, 1,1));
        request.setDepartureTime(LocalDateTime.of(2026, 3, 1, 1,1,1));
        request.setTickets(List.of());

        when(flightRepoMock.save(any(Flight.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Flight result = flightService.createFlight(request);

        assertEquals(FlightStatus.SCHEDULED, result.getFlightStatus());
        assertEquals(FlightClass.ECONOMY, result.getFlightClass());
        assertEquals(200, result.getPrice());
        assertEquals(150, result.getSeats());
        assertEquals("SOFIA", result.getToDestination());
        assertEquals("LONDON", result.getFromDestination());
        assertEquals(LocalDateTime.of(2026, 3, 3, 1,1), result.getArrivalTime());
        assertEquals(LocalDateTime.of(2026, 3, 1, 1,1,1), result.getDepartureTime());
        assertEquals(List.of(), result.getTickets());

        verify(flightRepoMock, times(1)).save(any(Flight.class));
    }

    @Test
    void testCreateFlightArrivalBeforeDeparture() {
        FlightRepository flightRepoMock = mock(FlightRepository.class);
        FlightService flightService = new FlightServiceImpl(flightRepoMock);

        FlightCreationRequest request = new FlightCreationRequest();
        request.setArrivalTime(LocalDateTime.of(2026, 1, 1,1,1));
        request.setDepartureTime(LocalDateTime.of(2026, 2,1,1,1));

        assertThrows(IllegalArgumentException.class, () -> flightService.createFlight(request));
    }

    @Test
    void testUpdateFlightById() {
        FlightRepository flightRepoMock = mock(FlightRepository.class);
        FlightService flightService = new FlightServiceImpl(flightRepoMock);

        FlightUpdateRequest flightUpdateRequest = new FlightUpdateRequest();
        flightUpdateRequest.setFlightStatus(FlightStatus.CANCELLED);
        flightUpdateRequest.setPrice(13350);
        flightUpdateRequest.setArrivalTime(LocalDateTime.of(1995, 9, 9, 9, 9));
        flightUpdateRequest.setDepartureTime(LocalDateTime.of(1995, 8, 8, 8, 8));

        Flight existingFlight = new Flight();
        existingFlight.setFlightId(1L);
        existingFlight.setPrice(1000);
        existingFlight.setSeats(150);
        existingFlight.setFlightStatus(FlightStatus.SCHEDULED);
        existingFlight.setDepartureTime(LocalDateTime.of(2000, 3, 3, 3, 3));
        existingFlight.setArrivalTime(LocalDateTime.of(2000, 7, 7, 7, 7));

        when(flightRepoMock.findById(1L)).thenReturn(Optional.of(existingFlight));
        when(flightRepoMock.save(any(Flight.class))).thenAnswer(i -> i.getArgument(0));
        flightService.updateFlightById(1L, flightUpdateRequest);

        assertEquals(13350, existingFlight.getPrice());
        assertEquals(FlightStatus.CANCELLED, existingFlight.getFlightStatus());
        assertEquals(LocalDateTime.of(1995, 9, 9, 9, 9), existingFlight.getArrivalTime());
        assertEquals(LocalDateTime.of(1995, 8, 8, 8, 8), existingFlight.getDepartureTime());

        verify(flightRepoMock, times(1)).findById(1L);
    }

    @Test
    void testUpdateFlightWithNotExisting() {
        FlightRepository flightRepoMock = mock(FlightRepository.class);
        FlightService flightService = new FlightServiceImpl(flightRepoMock);

        FlightUpdateRequest flightUpdateRequest = new FlightUpdateRequest();

        when(flightRepoMock.findById(945L)).thenReturn(Optional.empty());

        assertThrows(FlightDoesNotExist.class, () -> flightService.updateFlightById(945L, flightUpdateRequest));
    }

    @Test
    void testDeleteFlight() {
        FlightRepository flightRepoMock = mock(FlightRepository.class);
        FlightService flightService = spy(new FlightServiceImpl(flightRepoMock));

        Flight flight = new Flight();
        flight.setTickets(List.of());
        flight.setFlightId(1L);

        when(flightRepoMock.findById(1L)).thenReturn(Optional.of(flight));

        doReturn(true).when(flightService).flightExists(1L);

        flightService.deleteFlight(1L);

        verify(flightService, times(1)).flightExists(1L);
        verify(flightRepoMock, times(1)).findById(1L);
        verify(flightRepoMock, times(1)).deleteById(1L);
    }
}
