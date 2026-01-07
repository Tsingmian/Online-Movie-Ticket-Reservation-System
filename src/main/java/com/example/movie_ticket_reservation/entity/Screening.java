package com.example.movie_ticket_reservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

@Entity
@Table(name = "screenings")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 对应 Movie.id
    @Column(nullable = false)
    private Long movieId;

    // 影厅编号
    @Column(nullable = false)
    private Long hallId;

    // 放映开始时间
    @Column(nullable = false)
    private LocalDateTime startTime;

    // 单张票价（单位：元）
    @Column(nullable = false)
    private Integer price;

    // getter / setter
}
