package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.entity.User;
import com.example.movie_ticket_reservation.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Test
    void testLoginSuccess() {
        User user = authService.login("admin", "admin123");
        assertNotNull(user);
    }

    @Test
    void testLoginFail() {
        User user = authService.login("admin", "wrong");
        assertNull(user);
    }
}
