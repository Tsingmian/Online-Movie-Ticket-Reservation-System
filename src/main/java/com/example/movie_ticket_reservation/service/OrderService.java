package com.example.movie_ticket_reservation.service;


import com.example.movie_ticket_reservation.entity.Order;
import com.example.movie_ticket_reservation.entity.Screening;
import com.example.movie_ticket_reservation.entity.Seat;
import com.example.movie_ticket_reservation.mapper.OrderMapper;
import com.example.movie_ticket_reservation.mapper.ScreeningMapper;
import com.example.movie_ticket_reservation.mapper.SeatMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Service
//public class OrderService {
//
//    private final ScreeningMapper screeningMapper;
//    private final SeatMapper seatMapper;
//    private final OrderMapper orderMapper;
//
//    public OrderService(ScreeningMapper screeningMapper,
//                        SeatMapper seatMapper,
//                        OrderMapper orderMapper) {
//        this.screeningMapper = screeningMapper;
//        this.seatMapper = seatMapper;
//        this.orderMapper = orderMapper;
//    }
//
//    /**
//     * 下单核心流程（支持多座位）
//     */
//    @Transactional
//    public Order placeOrder(Long userId,
//                            Long screeningId,
//                            List<Long> seatIds) {
//
//        // 校验场次
//        Screening screening = screeningMapper.selectById(screeningId);
//        if (screening == null) {
//            throw new RuntimeException("场次不存在");
//        }
//
//        // 校验座位归属和状态
//        for (Long seatId : seatIds) {
//            Seat seat = seatMapper.selectById(seatId);
//            if (seat == null)
//                throw new RuntimeException("座位不存在: " + seatId);
//            if (!seat.getScreeningId().equals(screeningId)) {
//                throw new RuntimeException("座位不属于该场次: " + seatId);
//            }
//            if (seat.getStatus() != 0) {
//                throw new RuntimeException("座位已被占用: " + seatId);
//            }
//        }
//
//        // 批量锁座（并发安全）
//        int lockedCount = seatMapper.lockSeats(seatIds);
//        if (lockedCount != seatIds.size()) {
//            throw new RuntimeException("部分座位已被占用");
//        }
//
//        // 创建订单
////        BigDecimal totalPrice = screening.getPrice().multiply(BigDecimal.valueOf(seatIds.size()));
////        Order order = new Order();
////        order.setUserId(userId);
////        order.setScreeningId(screeningId);
////        order.setPrice(totalPrice);
////        order.setCreateTime(LocalDateTime.now());
////        order.setStatus(0); // 未支付
//        // 计算价格（含折扣）
//        int seatCount = seatIds.size();
//        BigDecimal unitPrice = screening.getPrice();
//        BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(seatCount));
//
//        // >= 5 * 0.9 折扣
//        if (seatCount > 5) {
//            totalPrice = totalPrice.multiply(BigDecimal.valueOf(0.9));
//        }
//
//        // 创建订单
//        Order order = new Order();
//        order.setUserId(userId);
//        order.setScreeningId(screeningId);
//        order.setSeatCount(seatCount);
//        order.setPrice(totalPrice);
//        order.setStatus(0);
//        order.setCreateTime(LocalDateTime.now());
//
//        orderMapper.insertOrder(order);
//
//        int rows = orderMapper.insertOrder(order);
//        if (rows != 1) throw new RuntimeException("订单创建失败");
//
//        // 插入 order_seat 表
//        orderMapper.insertOrderSeats(order.getId(), seatIds);
//
//        return order;
//    }
//
//    /**
//     * 支付订单
//     */
//    @Transactional
//    public Order payOrder(Long orderId) {
//        Order order = orderMapper.selectById(orderId);
//        if (order == null)
//            throw new RuntimeException("订单不存在");
//        if (order.getStatus() != 0)
//            throw new RuntimeException("订单已支付或取消");
//
//        // 获取订单座位
//        List<Long> seatIds = orderMapper.selectSeatIdsByOrderId(orderId);
//
//        // 标记座位已售
//        for (Long seatId : seatIds) {
//            seatMapper.sellSeat(seatId);
//        }
//
//        // 更新订单状态
//        orderMapper.updateStatus(orderId, 1, LocalDateTime.now());
//        order.setStatus(1);
//        order.setPayTime(LocalDateTime.now());
//
//        return order;
//    }
//}

//@Service
//public class OrderService {
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    @Transactional
//    public Long createOrder(Long userId, Long screeningId, List<Long> seatIds, BigDecimal ticketPrice) {
//        int seatCount = seatIds.size();
//        BigDecimal totalPrice = ticketPrice.multiply(BigDecimal.valueOf(seatCount));
//
//        // 超过5张打九折
//        if(seatCount > 5){
//            totalPrice = totalPrice.multiply(new BigDecimal("0.9"));
//        }
//
//        Order order = new Order();
//        order.setUserId(userId);
//        order.setScreeningId(screeningId);
//        order.setSeatCount(seatCount);
//        order.setPrice(totalPrice);
//        order.setStatus(1); // 已支付
//        order.setPayTime(LocalDateTime.now());
//        orderMapper.insertOrder(order);
//
//        orderMapper.insertOrderSeats(order.getId(), seatIds);
//
//        return order.getId();
//    }
//
//    public Map<String,Object> getUserOrders(Long userId, Integer status, int page, int size){
//        int offset = (page - 1) * size;
//        List<Order> orders = orderMapper.selectOrdersByUser(userId, status, offset, size);
//        int total = orderMapper.countOrdersByUser(userId, status);
//
//        Map<String,Object> result = new HashMap<>();
//        result.put("orders", orders);
//        result.put("total", total);
//        result.put("page", page);
//        result.put("size", size);
//        return result;
//    }
//
//    public List<Map<String,Object>> getOrderSeats(Long orderId){
//        return orderMapper.selectSeatsByOrder(orderId);
//    }
//}

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final SeatMapper seatMapper;
    private final ScreeningMapper screeningMapper;

    public OrderService(OrderMapper orderMapper, SeatMapper seatMapper, ScreeningMapper screeningMapper) {
        this.orderMapper = orderMapper;
        this.seatMapper = seatMapper;
        this.screeningMapper = screeningMapper;
    }

//    @Autowired
//    private OrderService orderService;

//    下单逻辑

    @Transactional
    public Order placeOrder(Long userId, Long screeningId, List<Long> seatIds) {
        Screening screening = screeningMapper.selectById(screeningId);
        if(screening == null) throw new RuntimeException("场次不存在");

        // 校验座位
        for(Long seatId : seatIds){
            Seat seat = seatMapper.selectById(seatId);
            if(seat == null) throw new RuntimeException("座位不存在: " + seatId);
            if(!seat.getScreeningId().equals(screeningId))
                throw new RuntimeException("座位不属于该场次: " + seatId);
            if(seat.getStatus() != 0)
                throw new RuntimeException("座位已被占用: " + seatId);
        }

        // 锁座
        int locked = seatMapper.lockSeats(seatIds);
        if(locked != seatIds.size()) throw new RuntimeException("部分座位已被占用");

        // 计算总价（每张票价格为 screening.price  折扣）
        BigDecimal totalPrice = screening.getPrice().multiply(BigDecimal.valueOf(seatIds.size()));
        if(seatIds.size() >= 5){
            totalPrice = totalPrice.multiply(BigDecimal.valueOf(0.9));
        }

        // 创建订单
        Order order = new Order();
        order.setUserId(userId);
        order.setScreeningId(screeningId);
        order.setSeatCount(seatIds.size());
        order.setPrice(totalPrice);
        order.setStatus(0); // 未支付
        order.setCreateTime(LocalDateTime.now());
        orderMapper.insertOrder(order);

        // 插入订单座位关系
        orderMapper.insertOrderSeats(order.getId(), seatIds);

        return order;
    }

    public Map<String,Object> getUserOrders(Long userId, Integer status, int page, int size){
        int offset = (page - 1) * size;
        List<Order> orders = orderMapper.selectOrdersByUser(userId, status, offset, size);
        int total = orderMapper.countOrdersByUser(userId, status);

        Map<String,Object> result = new HashMap<>();
        result.put("orders", orders);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        return result;
    }

    public List<Map<String, Object>> getOrderSeats(Long orderId) {
        return orderMapper.selectSeatsByOrder(orderId);
    }
//public List<Long> getOrderSeats(Long orderId) {
//    return orderMapper.selectSeatIdsByOrderId(orderId);
//}


    public int countUserOrders(Long userId) {
        return orderMapper.countOrdersByUser(userId, null);
    }

    public Order getOrderById(Long orderId) {
        return orderMapper.selectById(orderId);
    }

    public Map<String, Object> getAllOrders(Integer status, int page, int size) {
        int offset = (page - 1) * size;
        List<Order> orders = orderMapper.selectOrders(status, offset, size); // 需在 Mapper 写对应方法
        int total = orderMapper.countOrders(status); // 统计总数

        Map<String, Object> result = new HashMap<>();
        result.put("orders", orders);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        return result;
    }


    @Transactional
    public void deleteOrder(Long orderId) {
        // 查询订单座位
        List<Long> seatIds = orderMapper.selectSeatIdsByOrderId(orderId);

        // 释放座位
        seatMapper.releaseSeats(seatIds);

        // 删除 order_seat 关系
        orderMapper.deleteOrderSeats(orderId);

        // 删除订单
        orderMapper.deleteOrder(orderId);
    }


    @Transactional
    public void updateOrder(Long orderId, Map<String, Object> updates) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) throw new RuntimeException("订单不存在");

        // 更新状态
        if (updates.containsKey("status")) {
            Integer status = Integer.valueOf(updates.get("status").toString());
            orderMapper.updateStatus(orderId, status, LocalDateTime.now());
            order.setStatus(status);
        }

        // 更新座位
        if (updates.containsKey("seatIds")) {
            List<Long> newSeatIds = ((List<?>) updates.get("seatIds"))
                    .stream().map(o -> Long.valueOf(o.toString())).toList();

            // 先释放旧座位
            List<Long> oldSeatIds = orderMapper.selectSeatIdsByOrderId(orderId);
            seatMapper.releaseSeats(oldSeatIds);

            // 锁定新座位
            int locked = seatMapper.lockSeats(newSeatIds);
            if (locked != newSeatIds.size()) throw new RuntimeException("部分座位已被占用");

            // 更新 order_seat 表
            orderMapper.deleteOrderSeats(orderId);
            orderMapper.insertOrderSeats(orderId, newSeatIds);

            order.setSeatCount(newSeatIds.size());
        }

        // 可以根据需要更新价格、支付状态等
        if (updates.containsKey("price")) {
            BigDecimal price = new BigDecimal(updates.get("price").toString());
            order.setPrice(price);
        }

        orderMapper.updateOrder(order); // 更新订单信息
    }

    @Transactional
    public Order payOrder(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) throw new RuntimeException("订单不存在");
        if (order.getStatus() != 0) throw new RuntimeException("订单已支付或已取消");

        // 获取座位ID
        List<Long> seatIds = orderMapper.selectSeatIdsByOrderId(orderId);

        // 标记座位为已售（status=1）
        seatMapper.sellSeats(seatIds);

        // 更新订单状态为已支付
        orderMapper.updateStatus(orderId, 1, LocalDateTime.now());
        order.setStatus(1);
        order.setPayTime(LocalDateTime.now());

        return order;
    }

    public void updateOrderStatus(Long orderId, String statusStr) {
        // 将字符串状态转为数据库对应的整数值
        Integer status;
        if ("PAID".equals(statusStr)) {
            status = 1; // 假设 1 = PAID
        } else if ("CANCELLED".equals(statusStr)) {
            status = 2; // 假设 2 = CANCELLED
        } else {
            throw new IllegalArgumentException("不支持的状态: " + statusStr);
        }

        // 调用 Mapper 更新状态 + 支付时间（如果是支付）
        LocalDateTime payTime = "PAID".equals(statusStr) ? LocalDateTime.now() : null;
        int updated = orderMapper.updateStatus(orderId, status, payTime);

        if (updated == 0) {
            throw new RuntimeException("订单不存在或状态未更新");
        }
    }
}
