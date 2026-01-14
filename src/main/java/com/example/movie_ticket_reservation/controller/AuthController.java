package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.entity.User;
import com.example.movie_ticket_reservation.service.AuthService;
import org.springframework.http.ResponseEntity;
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

//    @PostMapping("/login")
//    public Map<String, Object> login(@RequestBody Map<String, String> req) {
//
// //        System.out.println("收到登录请求: " + req);
// //        // 临时返回一个假用户测试
// //        return Map.of("id", 1, "username", req.get("username"), "role", "user");
//
//        User user = authService.login(
//                req.get("username"),
//                req.get("password")
//        );
//
//        return Map.of(
//                "id", user.getId(),
//                "username", user.getUsername(),
//                "role", user.getRole()
//        );
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {

        try {
            User user = authService.login(
                    req.get("username"),
                    req.get("password")
            );

            return ResponseEntity.ok(Map.of(
                    "id", user.getId(),
                    "username", user.getUsername(),
                    "role", user.getRole()
            ));

        } catch (Exception e) {
            return ResponseEntity
                    .status(401)
                    .body("用户名或密码错误");
        }
    }

     //查用户
    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestParam Long userId) {
        User user = authService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body("用户不存在");
        }
        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "role", user.getRole()
        ));
    }

}
