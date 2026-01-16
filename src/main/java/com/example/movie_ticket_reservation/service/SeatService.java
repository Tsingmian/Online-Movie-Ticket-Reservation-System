package com.example.movie_ticket_reservation.service;

import com.example.movie_ticket_reservation.entity.Seat;
import com.example.movie_ticket_reservation.mapper.SeatMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatMapper seatMapper;

    public SeatService(SeatMapper seatMapper) {
        this.seatMapper = seatMapper;
    }

    //  普通查询
    public List<Seat> getSeatsByScreening(Long screeningId) {
        return seatMapper.selectByScreeningId(screeningId);
    }

    public Seat getSeatById(Long id) {
        return seatMapper.selectById(id);
    }

    //  管理端增删改查
    public List<Seat> getAllSeats() {
        return seatMapper.selectAll();
    }

    public void addSeat(Seat seat) {
        seatMapper.insert(seat);
    }

    public void updateSeat(Seat seat) {
        seatMapper.update(seat);
    }

    public void deleteSeat(Long id) {
        seatMapper.delete(id);
    }

    //  可用座位查询
    public List<Seat> getAvailableSeats(Long screeningId) {
        return seatMapper.selectAvailableByScreeningId(screeningId);
    }

}
