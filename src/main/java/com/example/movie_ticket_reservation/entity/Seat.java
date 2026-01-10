package com.example.movie_ticket_reservation.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Seat {

    @Setter
    @Getter
    private Long id;
    @Setter
    @Getter
    private Long screeningId;
    @Getter
    @Setter
    private Integer rowNum;
    @Setter
    @Getter
    private Integer colNum;
    @Setter
    @Getter
    private Integer status; // 0=可售 1=锁定 2=已售
    private LocalDateTime lockTime;

}
