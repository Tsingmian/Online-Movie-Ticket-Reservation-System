package com.example.movie_ticket_reservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(
    name = "seats"
)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 所属场次（Screening.id）
    @Column(nullable = false)
    private Long screeningId;

    // 行号（从 1 开始）
    @Column(nullable = false)
    private Integer rowNum;

    // 列号（从 1 开始）
    @Column(nullable = false)
    private Integer colNum;

    /**
     * 座位状态
     * 0 = 可售
     * 1 = 已锁定（下单未支付）
     * 2 = 已售
     */
    @Column(nullable = false)
    private Integer status;

    // getter / setter
}
