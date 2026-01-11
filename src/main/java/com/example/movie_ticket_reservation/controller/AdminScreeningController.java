package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.entity.Screening;
import com.example.movie_ticket_reservation.entity.Seat;
import com.example.movie_ticket_reservation.service.ScreeningService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/screenings") // 管理端前缀
@CrossOrigin(origins = "http://localhost:5173")
public class AdminScreeningController {

    private final ScreeningService screeningService;

    public AdminScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    // 查询所有场次（可按电影筛选）
    @GetMapping
    public Response getScreenings(@RequestParam(value = "movieId", required = false) Long movieId) {
        List<Screening> screenings;
        if (movieId != null) {
            screenings = screeningService.getByMovieId(movieId);
        } else {
            screenings = screeningService.getAllScreenings();
        }
        return new Response(1, screenings);
    }

    // 查询单个场次
    @GetMapping("/{id}")
    public Response getScreeningById(@PathVariable Long id) {
        Screening screening = screeningService.getById(id);
        return new Response(1, screening);
    }

    // 新增场次
    @PostMapping
    public Response addScreening(@RequestBody Screening screening) {
        screening.setCreateTime(LocalDateTime.now());
        screeningService.addScreening(screening);
        return new Response(1, "新增成功");
    }

    // 更新场次
    @PutMapping("/{id}")
    public Response updateScreening(@PathVariable Long id, @RequestBody Screening screening) {
        screening.setId(id);
        screeningService.updateScreening(screening);
        return new Response(1, "更新成功");
    }

    // 删除场次
    @DeleteMapping("/{id}")
    public Response deleteScreening(@PathVariable Long id) {
        screeningService.deleteScreening(id);
        return new Response(1, "删除成功");
    }

    // 查询座位（可增删改座位可以单独写 SeatController）
    @GetMapping("/{screeningId}/seats")
    public Response getSeats(@PathVariable Long screeningId) {
        List<Seat> seats = screeningService.getSeatsByScreening(screeningId);
        return new Response(1, seats);
    }

    @PutMapping("/{screeningId}/seats")
    public Response updateSeats(@PathVariable Long screeningId, @RequestBody List<Seat> seats) {
        screeningService.updateSeats(screeningId, seats);
        return new Response(1, "座位更新成功");
    }

    // 响应类
    static class Response {
        private int status;
        private Object data;

        public Response(int status, Object data) {
            this.status = status;
            this.data = data;
        }

        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public Object getData() { return data; }
        public void setData(Object data) { this.data = data; }
    }
}
