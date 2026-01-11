package com.example.movie_ticket_reservation.service;

import com.example.movie_ticket_reservation.mapper.UserMapper;
import com.example.movie_ticket_reservation.entity.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserMapper userMapper;

    public AuthService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 登录方法
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);

        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("密码错误");
        }

        return user;
    }

    // 根据用户ID获取用户信息
    public User getUserById(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return user;
    }
}
