package com.example.movie_ticket_reservation.service;

import com.example.movie_ticket_reservation.entity.Seat;
import com.example.movie_ticket_reservation.mapper.SeatMapper;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    private final SeatMapper seatMapper;

    public SeatService(SeatMapper seatMapper) {
        this.seatMapper = seatMapper;
    }

    /**
     * 锁座（仅做一件事）
     */
    public boolean lockSeat(Long seatId) {
        return seatMapper.lockSeat(seatId) == 1;
    }
}
