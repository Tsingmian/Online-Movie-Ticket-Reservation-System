package com.example.movie_ticket_reservation.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    @Setter
    @Getter
    private Long id;

    // 下单用户
    @Setter
    @Getter
    private Long userId;

    // 场次
    @Setter
    @Getter
    private Long screeningId;

    // 座位ID（第一阶段：单座）
//    @Setter
//    @Getter
//    private List<Long> seatId;

    @Getter
    @Setter
    private Integer seatCount;

    // 订单价格（元）
    @Setter
    @Getter
    private BigDecimal price;

//    private LocalDateTime createTime;
    @Setter
    @Getter
    private LocalDateTime payTime;

    //  订单  0 = 未支付 1 = 已支付  2 = 已取消

    @Setter
    @Getter
    private Integer status;

    // 下单时间
    @Setter
    @Getter
    private LocalDateTime createTime;

}
