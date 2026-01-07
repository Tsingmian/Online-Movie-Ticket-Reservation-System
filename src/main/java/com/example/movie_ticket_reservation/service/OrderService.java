package com.example.movie_ticket_reservation.service;

import com.example.movie_ticket_reservation.entity.Order;
import com.example.movie_ticket_reservation.entity.Screening;
import com.example.movie_ticket_reservation.entity.Seat;
import com.example.movie_ticket_reservation.mapper.OrderMapper;
import com.example.movie_ticket_reservation.mapper.ScreeningMapper;
import com.example.movie_ticket_reservation.mapper.SeatMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
     * 下单核心流程
     */
    @Transactional
    public void placeOrder(Long userId,
                           Long screeningId,
                           Long seatId) {

        // 1️⃣ 校验场次
        Screening screening = screeningMapper.selectById(screeningId);
        if (screening == null) {
            throw new RuntimeException("场次不存在");
        }

        // 2️⃣ 校验座位
        Seat seat = seatMapper.selectById(seatId);
        if (seat == null) {
            throw new RuntimeException("座位不存在");
        }

        if (!seat.getScreeningId().equals(screeningId)) {
            throw new RuntimeException("座位不属于该场次");
        }

        // 3️⃣ 锁座（并发安全）
        int locked = seatMapper.lockSeat(seatId);
        if (locked != 1) {
            throw new RuntimeException("座位已被占用");
        }

        // 4️⃣ 创建订单
        Order order = new Order();
        order.setUserId(userId);
        order.setScreeningId(screeningId);
        order.setSeatId(seatId);
        order.setPrice(screening.getPrice());
        order.setCreateTime(LocalDateTime.now());
        order.setStatus(0); // 未支付

        int rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new RuntimeException("订单创建失败");
        }
    }

}
