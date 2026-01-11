package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.entity.Order;
import com.example.movie_ticket_reservation.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
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


//public class OrderController {
//
//    private final OrderService orderService;
//
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    // 下单接口
//    @PostMapping("/place")
//    public Map<String,Object> placeOrder(@RequestBody PlaceOrderRequest req) {
//        Order order = orderService.placeOrder(req.getUserId(), req.getScreeningId(), req.getSeatIds());
//        Map<String,Object> result = new HashMap<>();
//        result.put("status","ok");
//        result.put("order", order);
//        return result;
//    }
//
//    // 支付接口
//    @PostMapping("/pay")
//    public Map<String,Object> payOrder(@RequestBody Map<String, Long> body) {
//        Long orderId = body.get("orderId");
//        Order order = orderService.payOrder(orderId);
//        Map<String,Object> result = new HashMap<>();
//        result.put("status","paid");
//        result.put("order", order);
//        return result;
//    }
//}
//
//// 请求 DTO
//class PlaceOrderRequest {
//    private Long userId;
//    private Long screeningId;
//    private List<Long> seatIds;
//    // getter & setter
//    public Long getUserId() { return userId; }
//    public void setUserId(Long userId) { this.userId = userId; }
//    public Long getScreeningId() { return screeningId; }
//    public void setScreeningId(Long screeningId) { this.screeningId = screeningId; }
//    public List<Long> getSeatIds() { return seatIds; }
//    public void setSeatIds(List<Long> seatIds) { this.seatIds = seatIds; }
//}

//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    // 提交订单
//    @PostMapping("/create")
//    public ResponseEntity<Map<String,Object>> createOrder(@RequestBody Map<String,Object> request) {
//        Long userId = Long.valueOf(request.get("userId").toString());
//        Long screeningId = Long.valueOf(request.get("screeningId").toString());
//        List<Integer> seatIdsInt = (List<Integer>) request.get("seatIds");
//        List<Long> seatIds = seatIdsInt.stream().map(Long::valueOf).toList();
//        BigDecimal price = new BigDecimal(request.get("ticketPrice").toString());
//
//        Long orderId = orderService.createOrder(userId, screeningId, seatIds, price);
//        Map<String,Object> result = new HashMap<>();
//        result.put("orderId", orderId);
//
//        // 返回订单ID，前端可以跳转到订单详情页
//        return ResponseEntity.ok(result);
//    }
//
//    // 获取用户订单列表（分页 + 状态筛选）
//    @GetMapping("/user/{userId}")
//    public Map<String,Object> getUserOrders(@PathVariable Long userId,
//                                            @RequestParam(required = false) Integer status,
//                                            @RequestParam(defaultValue = "1") int page,
//                                            @RequestParam(defaultValue = "10") int size){
//        return orderService.getUserOrders(userId, status, page, size);
//    }
//
//    // 获取订单详情（座位号）
//    @GetMapping("/{orderId}/seats")
//    public List<Map<String,Object>> getOrderSeats(@PathVariable Long orderId){
//        return orderService.getOrderSeats(orderId);
//    }
//}

public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 下单接口（之前的风格）
     * 请求体示例：
     * {
     * "userId": 1,
     * "screeningId": 2,
     * "seatIds": [1,2,3]
     * }
     */
//    @PostMapping("/place")
//    public Map<String, Object> placeOrder(@RequestBody Map<String, Object> req) {
//        Long userId = Long.valueOf(req.get("userId").toString());
//        Long screeningId = Long.valueOf(req.get("screeningId").toString());
//        List<Integer> seatIdsInt = (List<Integer>) req.get("seatIds");
//        List<Long> seatIds = seatIdsInt.stream().map(Long::valueOf).toList();
//
//        // 调用 Service 下单
//        Order order = orderService.placeOrder(userId, screeningId, seatIds);
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("status", "ok");
//        result.put("order", order);
//        return result;
//    }

//    @PostMapping("/place")
//    public Map<String, Object> placeOrder(@RequestBody Map<String, Object> req) {
//        Long userId = Long.valueOf(req.get("userId").toString());
//        Long screeningId = Long.valueOf(req.get("screeningId").toString());
//
//        // 安全解析 seatIds
//        List<Long> seatIds = ((List<?>) req.get("seatIds"))
//                .stream()
//                .map(o -> Long.valueOf(o.toString()))
//                .toList();
//
//        Order order = orderService.placeOrder(userId, screeningId, seatIds);
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("status", "ok");
//        result.put("order", order);
//        return result;
//    }
//
//
//    @GetMapping("/user/{userId}")
//    public Map<String,Object> getUserOrders(@PathVariable Long userId,
//                                            @RequestParam(defaultValue = "1") int page,
//                                            @RequestParam(defaultValue = "10") int size){
//        List<Order> list = orderService.getUserOrders(userId, page, size);
//        Map<String,Object> result = new HashMap<>();
//        Map<String,Object> result = orderService.getUserOrders(userId, status, page, size);
//        return result;
//    }

    // 创建订单
    @PostMapping("/place")
    public Map<String, Object> placeOrder(@RequestBody Map<String, Object> req) {
        Long userId = Long.valueOf(req.get("userId").toString());
        Long screeningId = Long.valueOf(req.get("screeningId").toString());

        List<Long> seatIds = ((List<?>) req.get("seatIds"))
                .stream()
                .map(o -> Long.valueOf(o.toString()))
                .toList();

        Order order = orderService.placeOrder(userId, screeningId, seatIds);

        Map<String, Object> result = new HashMap<>();
        result.put("status", "ok");
        result.put("order", order);
        return result;
    }

    // 获取用户订单列表（分页 + 状态筛选）
    @GetMapping("/user/{userId}")
    public Map<String, Object> getUserOrders(
            @PathVariable Long userId,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return orderService.getUserOrders(userId, status, page, size);
    }

    // 获取单个订单详情
    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }


    // 获取订单座位
    @GetMapping("/{orderId}/seats")
    public List<Map<String, Object>> getOrderSeats(@PathVariable Long orderId) {
        return orderService.getOrderSeats(orderId);
    }

}



//@PostMapping("/place")
//public ResponseEntity<?> placeOrder(@RequestBody Map<String, Object> req) {
//    try {
//        // 解析用户 ID
//        Long userId = Long.valueOf(req.get("userId").toString());
//        Long screeningId = Long.valueOf(req.get("screeningId").toString());
//
//        // seatIds 可能是 Integer 或 Long，安全转换
//        List<Long> seatIds = ((List<?>) req.get("seatIds")).stream()
//                .map(o -> Long.valueOf(o.toString()))
//                .toList();
//
//        // 调用 Service 下单
//        Order order = orderService.placeOrder(userId, screeningId, seatIds);
//
//        // 返回成功
//        Map<String, Object> result = new HashMap<>();
//        result.put("status", "ok");
//        result.put("order", order);
//        return ResponseEntity.ok(result);
//
//    } catch (Exception e) {
//        // 捕获异常并返回给前端
//        e.printStackTrace();
//        Map<String, Object> error = new HashMap<>();
//        error.put("status", "error");
//        error.put("message", e.getMessage());
//        return ResponseEntity.status(500).body(error);
//    }
//}



