package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.entity.Seat;
import com.example.movie_ticket_reservation.service.SeatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeatServiceTest {

    @Autowired
    private SeatService seatService;

    @Test
    void testQuerySeatsByScreening() {
        Long screeningId = 1L;
        List<Seat> seats = seatService.getSeatsByScreening(screeningId);
        assertNotNull(seats);
    }

    void testQueryAvailableSeats() {
        List<Seat> seats = seatService.getAvailableSeats(1L);
        assertFalse(seats.isEmpty());
    }

    @Test
    void testQueryAllSeatsByScreening() {
        List<Seat> seats = seatService.getSeatsByScreening(1L);
        assertFalse(seats.isEmpty());
    }

    @Test
    void testQueryAvailableSeatsEmpty() {
        List<Seat> seats = seatService.getAvailableSeats(999L);
        assertTrue(seats.isEmpty());
    }


}
