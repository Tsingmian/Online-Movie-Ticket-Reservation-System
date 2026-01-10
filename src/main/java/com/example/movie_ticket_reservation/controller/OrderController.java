package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.entity.Order;
import com.example.movie_ticket_reservation.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")

@CrossOrigin(origins = "http://localhost:5173")

//public class OrderController {
//
//    private final OrderService orderService;
//
//    public OrderController(OrderService orderService) {
//
//        this.orderService = orderService;
//    }
//
//    @PostMapping("/place")
//    public String placeOrder(@RequestParam Long userId,
//                             @RequestParam Long screeningId,
//                             @RequestParam Long seatId) {
//        orderService.placeOrder(userId, screeningId, seatId);
//        return "ok";
//    }
//}

//public class OrderController {
//
//    private final OrderService orderService;
//
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    /**
//     * 下单接口
//     */
//    @PostMapping("/place")
//    public Map<String,Object> placeOrder(@RequestParam Long userId,
//                                         @RequestParam Long screeningId,
//                                         @RequestBody List<Long> seatIds) {
//        Order order = orderService.placeOrder(userId, screeningId, seatIds);
//        Map<String,Object> result = new HashMap<>();
//        result.put("status","ok");
//        result.put("order", order);
//        return result;
//    }
//
//    /**
//     * 支付接口
//     */
//    @PostMapping("/pay")
//    public Map<String,Object> payOrder(@RequestParam Long orderId) {
//        Order order = orderService.payOrder(orderId);
//        Map<String,Object> result = new HashMap<>();
//        result.put("status","paid");
//        result.put("order", order);
//        return result;
//    }
//}


public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 下单接口
    @PostMapping("/place")
    public Map<String,Object> placeOrder(@RequestBody PlaceOrderRequest req) {
        Order order = orderService.placeOrder(req.getUserId(), req.getScreeningId(), req.getSeatIds());
        Map<String,Object> result = new HashMap<>();
        result.put("status","ok");
        result.put("order", order);
        return result;
    }

    // 支付接口
    @PostMapping("/pay")
    public Map<String,Object> payOrder(@RequestBody Map<String, Long> body) {
        Long orderId = body.get("orderId");
        Order order = orderService.payOrder(orderId);
        Map<String,Object> result = new HashMap<>();
        result.put("status","paid");
        result.put("order", order);
        return result;
    }
}

// 请求 DTO
class PlaceOrderRequest {
    private Long userId;
    private Long screeningId;
    private List<Long> seatIds;
    // getter & setter
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getScreeningId() { return screeningId; }
    public void setScreeningId(Long screeningId) { this.screeningId = screeningId; }
    public List<Long> getSeatIds() { return seatIds; }
    public void setSeatIds(List<Long> seatIds) { this.seatIds = seatIds; }
}

