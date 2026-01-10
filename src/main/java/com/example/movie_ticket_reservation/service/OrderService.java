package com.example.movie_ticket_reservation.service;

import com.example.movie_ticket_reservation.entity.Order;
import com.example.movie_ticket_reservation.entity.Screening;
import com.example.movie_ticket_reservation.entity.Seat;
import com.example.movie_ticket_reservation.mapper.OrderMapper;
import com.example.movie_ticket_reservation.mapper.ScreeningMapper;
import com.example.movie_ticket_reservation.mapper.SeatMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final ScreeningMapper screeningMapper;
    private final SeatMapper seatMapper;
    private final OrderMapper orderMapper;

    public OrderService(ScreeningMapper screeningMapper,
                        SeatMapper seatMapper,
                        OrderMapper orderMapper) {
        this.screeningMapper = screeningMapper;
        this.seatMapper = seatMapper;
        this.orderMapper = orderMapper;
    }

    /**
     * 下单核心流程（支持多座位）
     */
    @Transactional
    public Order placeOrder(Long userId,
                            Long screeningId,
                            List<Long> seatIds) {

        // 1️⃣ 校验场次
        Screening screening = screeningMapper.selectById(screeningId);
        if (screening == null) {
            throw new RuntimeException("场次不存在");
        }

        // 2️⃣ 校验座位归属和状态
        for (Long seatId : seatIds) {
            Seat seat = seatMapper.selectById(seatId);
            if (seat == null) throw new RuntimeException("座位不存在: " + seatId);
            if (!seat.getScreeningId().equals(screeningId)) {
                throw new RuntimeException("座位不属于该场次: " + seatId);
            }
            if (seat.getStatus() != 0) {
                throw new RuntimeException("座位已被占用: " + seatId);
            }
        }

        // 3️⃣ 批量锁座（并发安全）
        int lockedCount = seatMapper.lockSeats(seatIds);
        if (lockedCount != seatIds.size()) {
            throw new RuntimeException("部分座位已被占用");
        }

        // 4️⃣ 创建订单
        BigDecimal totalPrice = screening.getPrice().multiply(BigDecimal.valueOf(seatIds.size()));
        Order order = new Order();
        order.setUserId(userId);
        order.setScreeningId(screeningId);
        order.setPrice(totalPrice);
        order.setCreateTime(LocalDateTime.now());
        order.setStatus(0); // 未支付

        int rows = orderMapper.insertOrder(order);
        if (rows != 1) throw new RuntimeException("订单创建失败");

        // 5️⃣ 插入 order_seat 表
        orderMapper.insertOrderSeats(order.getId(), seatIds);

        return order;
    }

    /**
     * 支付订单
     */
    @Transactional
    public Order payOrder(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) throw new RuntimeException("订单不存在");
        if (order.getStatus() != 0) throw new RuntimeException("订单已支付或取消");

        // 1️⃣ 获取订单座位
        List<Long> seatIds = orderMapper.selectSeatIdsByOrderId(orderId);

        // 2️⃣ 标记座位已售
        for (Long seatId : seatIds) {
            seatMapper.sellSeat(seatId);
        }

        // 3️⃣ 更新订单状态
        orderMapper.updateStatus(orderId, 1, LocalDateTime.now());
        order.setStatus(1);
        order.setPayTime(LocalDateTime.now());

        return order;
    }
}
