package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 查询所有订单（可分页、可按状态筛选）
    @GetMapping
    public Map<String,Object> getAllOrders(
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return orderService.getAllOrders(status, page, size);
    }

    // 查询单个订单
    @GetMapping("/{orderId}")
    public Map<String,Object> getOrder(@PathVariable Long orderId) {
        Map<String,Object> result = new HashMap<>();
        result.put("status", "ok");
        result.put("order", orderService.getOrderById(orderId));
        return result;
    }

    // 删除订单
    @DeleteMapping("/{orderId}")
    public Map<String,Object> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        Map<String,Object> result = new HashMap<>();
        result.put("status", "ok");
        result.put("message", "删除成功");
        return result;
    }

    // 更新订单
    @PutMapping("/{orderId}")
    public Map<String,Object> updateOrder(
            @PathVariable Long orderId,
            @RequestBody Map<String, Object> updates) {
        orderService.updateOrder(orderId, updates);
        Map<String,Object> result = new HashMap<>();
        result.put("status", "ok");
        result.put("message", "更新成功");
        return result;
    }


    @PostMapping("/{orderId}/pay")
    public Map<String, Object> forcePay(@PathVariable Long orderId) {
        orderService.updateOrderStatus(orderId, "PAID");
        return Map.of("status", "ok", "message", "订单已强制支付");
    }

    @PostMapping("/{orderId}/cancel")
    public Map<String, Object> cancelOrder(@PathVariable Long orderId) {
        orderService.updateOrderStatus(orderId, "CANCELLED");
        return Map.of("status", "ok", "message", "订单已取消");
    }
}
