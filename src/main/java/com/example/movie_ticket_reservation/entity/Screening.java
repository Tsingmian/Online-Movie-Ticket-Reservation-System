package com.example.movie_ticket_reservation.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Screening {

    @Setter
    @Getter
    private Long id;

    // 对应 Movie.id
    @Setter
    @Getter
    private Long movieId;

    // 影厅编号
    @Setter
    @Getter
    private String  hallName;

    // 放映开始时间
    @Setter
    @Getter
    private LocalDateTime startTime;

    @Setter
    @Getter
    private LocalDateTime endTime;

    // 单张票价（单位：元）
    @Setter
    @Getter
    private BigDecimal price;

    @Setter
    @Getter
    private Integer status; // 1=可售 0=停售

    @Setter
    @Getter
    private LocalDateTime createTime;

    // ===== getter / setter =====


}
