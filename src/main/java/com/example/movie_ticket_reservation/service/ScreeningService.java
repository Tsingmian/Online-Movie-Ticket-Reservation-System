package com.example.movie_ticket_reservation.service;

import com.example.movie_ticket_reservation.entity.Seat;
import com.example.movie_ticket_reservation.mapper.SeatMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningService {

    private final SeatMapper seatMapper;

    public ScreeningService(SeatMapper seatMapper) {
        this.seatMapper = seatMapper;
    }

    public List<Seat> getSeatsByScreening(Long screeningId) {
        return seatMapper.selectByScreeningId(screeningId);
    }
}
