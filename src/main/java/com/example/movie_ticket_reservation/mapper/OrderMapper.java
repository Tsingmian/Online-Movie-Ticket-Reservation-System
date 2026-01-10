package com.example.movie_ticket_reservation.mapper;

import com.example.movie_ticket_reservation.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 插入订单，返回自增主键
     */
    int insertOrder(Order order);

    /**
     * 批量插入订单座位
     */
    int insertOrderSeats(@Param("orderId") Long orderId,
                         @Param("seatIds") List<Long> seatIds);

    /**
     * 查询订单
     */
    Order selectById(Long id);

    /**
     * 查询订单座位
     */
    List<Long> selectSeatIdsByOrderId(Long orderId);

    /**
     * 更新订单状态
     */
    int updateStatus(@Param("id") Long id,
                     @Param("status") Integer status,
                     @Param("payTime") LocalDateTime payTime);
}
