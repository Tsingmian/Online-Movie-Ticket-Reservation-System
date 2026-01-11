package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.entity.Screening;
import com.example.movie_ticket_reservation.entity.Seat;
import com.example.movie_ticket_reservation.service.ScreeningService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screenings")
@CrossOrigin(origins = "http://localhost:5173")
public class ScreeningController {

    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    // 根据电影 ID 获取场次列表
    //    @GetMapping
    //    public List<Screening> getScreeningsByMovieId(@RequestParam Long movieId) {
    //        return screeningService.getByMovieId(movieId);
    //    }
    @GetMapping
    public List<Screening> getByMovieId(@RequestParam Long movieId) {
        return screeningService.getByMovieId(movieId);
    }

    @GetMapping("/{screeningId}")
    public Screening getById(@PathVariable Long screeningId) {
        return screeningService.getById(screeningId);
    }



    // ② 根据场次 ID 获取座位
    @GetMapping("/{screeningId}/seats")
    public List<Seat> getSeats(@PathVariable Long screeningId) {
        return screeningService.getSeatsByScreening(screeningId);
    }
}

