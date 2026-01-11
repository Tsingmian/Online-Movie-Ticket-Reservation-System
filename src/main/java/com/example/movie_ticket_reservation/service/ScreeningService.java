package com.example.movie_ticket_reservation.service;

import com.example.movie_ticket_reservation.entity.Screening;
import com.example.movie_ticket_reservation.entity.Seat;
import com.example.movie_ticket_reservation.mapper.ScreeningMapper;
import com.example.movie_ticket_reservation.mapper.SeatMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningService {

    private final ScreeningMapper screeningMapper;
    private final SeatMapper seatMapper;

    public ScreeningService(ScreeningMapper screeningMapper,
                            SeatMapper seatMapper) {
        this.screeningMapper = screeningMapper;
        this.seatMapper = seatMapper;
    }

    // ✅ 新增：根据电影查场次
    public List<Screening> getByMovieId(Long movieId) {
        return screeningMapper.selectByMovieId(movieId);
    }

    // ✅ 已有：根据场次查座位
    public List<Seat> getSeatsByScreening(Long screeningId) {
        return seatMapper.selectByScreeningId(screeningId);
    }

    public Screening getById(Long id) {
        return screeningMapper.selectById(id);
    }

}
