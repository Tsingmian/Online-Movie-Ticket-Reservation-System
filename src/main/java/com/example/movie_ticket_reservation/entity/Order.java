package com.example.movie_ticket_reservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 下单用户
    @Column(nullable = false)
    private Long userId;

    // 场次
    @Column(nullable = false)
    private Long screeningId;

    /**
     * 座位ID列表
     * 例如：1,2,3
     * 第一阶段用字符串，避免复杂关联
     */
    @Column(nullable = false, length = 255)
    private String seatIds;

    // 订单总价（单位：元）
    @Column(nullable = false)
    private Integer totalPrice;

    /**
     * 订单状态
     * 0 = 未支付
     * 1 = 已支付
     * 2 = 已取消
     */
    @Column(nullable = false)
    private Integer status;

    // 下单时间
    @Column(nullable = false)
    private LocalDateTime createTime;

    // getter / setter
}
