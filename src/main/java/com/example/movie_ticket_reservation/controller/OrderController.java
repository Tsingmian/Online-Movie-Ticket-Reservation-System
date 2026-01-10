package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")

@CrossOrigin(origins = "http://localhost:5173")

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }

    @PostMapping("/place")
    public String placeOrder(@RequestParam Long userId,
                             @RequestParam Long screeningId,
                             @RequestParam Long seatId) {
        orderService.placeOrder(userId, screeningId, seatId);
        return "ok";
    }
}
