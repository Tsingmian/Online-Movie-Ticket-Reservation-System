package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    void testSeatLock() {
        orderService.placeOrder(1L, 1L, List.of(1L));
        Assertions.assertThrows(
            RuntimeException.class,
            () -> orderService.placeOrder(2L, 1L, List.of(1L))
        );
    }
}
