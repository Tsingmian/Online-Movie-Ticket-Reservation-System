package com.example.movie_ticket_reservation.mapper;

import com.example.movie_ticket_reservation.entity.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SeatMapper {

    /**
     * 根据座位ID查询座位（用于展示或校验归属）
     */
    Seat selectById(@Param("id") Long id);

    /**
     * 查询某场次所有座位
     */
    List<Seat> selectByScreeningId(@Param("screeningId") Long screeningId);

    /**
     * 锁定单个座位（核心并发控制）
     * 只有 status = 0（空闲）才能成功
     * 返回值 = 1 表示锁定成功，0 表示锁定失败
     */
    int lockSeat(@Param("id") Long id);

    /**
     * 批量锁定座位（传入座位ID列表）
     * 返回成功锁定的数量
     */
    int lockSeats(@Param("seatIds") List<Long> seatIds);

    /**
     * 支付成功后，把座位状态改为已售
     */
    int sellSeat(@Param("id") Long id);

    /**
     * 批量支付成功
     */
    int sellSeats(@Param("seatIds") List<Long> seatIds);

    /**
     * 释放锁定座位（超时或取消订单）
     */
    int releaseSeat(@Param("id") Long id);

    /**
     * 批量释放锁定座位
     */
    int releaseSeats(@Param("seatIds") List<Long> seatIds);

}
