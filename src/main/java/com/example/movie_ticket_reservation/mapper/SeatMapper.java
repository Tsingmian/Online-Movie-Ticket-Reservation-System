package com.example.movie_ticket_reservation.mapper;

import com.example.movie_ticket_reservation.entity.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SeatMapper {

    // 根据 id 查座位（只用于展示 / 校验归属）
    Seat selectById(@Param("id") Long id);

    /**
     * 锁定座位（核心并发控制）
     * 只有 status = 0 才能成功
     */
    int lockSeat(@Param("id") Long id);
}
