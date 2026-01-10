package com.example.movie_ticket_reservation.controller;

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

    /**
     * 获取指定场次的座位列表
     */
    @GetMapping("/{screeningId}/seats")
    public List<Seat> getSeats(@PathVariable Long screeningId) {
        return screeningService.getSeatsByScreening(screeningId);
    }
}
