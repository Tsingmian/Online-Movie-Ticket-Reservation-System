package com.example.movie_ticket_reservation.mapper;

import com.example.movie_ticket_reservation.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    // 插入订单，返回自增主键
    int insertOrder(Order order);

    // 批量插入订单座位
    int insertOrderSeats(@Param("orderId") Long orderId, @Param("seatIds") List<Long> seatIds);

    // 根据订单ID查询订单
    Order selectById(Long id);

    // 根据订单ID查询座位ID列表
    List<Long> selectSeatIdsByOrderId(Long orderId);

    // 更新订单状态
    int updateStatus(@Param("id") Long id, @Param("status") Integer status, @Param("payTime") LocalDateTime payTime);

    // 分页查询用户订单
    List<Order> selectOrdersByUser(@Param("userId") Long userId,
                                   @Param("status") Integer status,
                                   @Param("offset") Integer offset,
                                   @Param("limit") Integer limit);

    List<Map<String, Object>> selectSeatsByOrder(@Param("orderId") Long orderId);

    // 查询用户订单总数
    int countOrdersByUser(@Param("userId") Long userId,
                          @Param("status") Integer status);

    List<Order> selectOrders(@Param("status") Integer status,
                             @Param("offset") int offset,
                             @Param("size") int size);

    int countOrders(@Param("status") Integer status);

    void deleteOrderSeats(@Param("orderId") Long orderId);

    void deleteOrder(@Param("orderId") Long orderId);

    void updateOrder(Order order);


}
