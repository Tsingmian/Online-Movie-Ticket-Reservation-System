package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.entity.User;
import com.example.movie_ticket_reservation.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> req) {

//        System.out.println("收到登录请求: " + req);
//        // 临时返回一个假用户，方便测试
//        return Map.of("id", 1, "username", req.get("username"), "role", "user");

        User user = authService.login(
                req.get("username"),
                req.get("password")
        );

        return Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "role", user.getRole()
        );
    }

    @GetMapping("/me")
    public Object me(@RequestParam Long userId) {
        // 简化版（后续可改为 token）
        return userId;
    }
}
