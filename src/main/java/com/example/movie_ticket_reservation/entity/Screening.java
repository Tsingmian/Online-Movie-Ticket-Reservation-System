package com.example.movie_ticket_reservation.entity;

import java.time.LocalDateTime;

public class Screening {

    private Long id;

    // 对应 Movie.id
    private Long movieId;

    // 影厅编号
    private String  hallName;

    // 放映开始时间
    private LocalDateTime startTime;

    // 单张票价（单位：元）
    private Integer price;

    // ===== getter / setter =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getHallId() {
        return hallName;
    }

    public void setHallId(String hallId) {
        this.hallName = hallId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
