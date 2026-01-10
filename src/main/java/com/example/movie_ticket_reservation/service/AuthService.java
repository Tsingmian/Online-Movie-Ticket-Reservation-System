package com.example.movie_ticket_reservation.service;

import com.example.movie_ticket_reservation.mapper.UserMapper;
import org.springframework.stereotype.Service;
import com.example.movie_ticket_reservation.entity.User;


@Service
public class AuthService {

    private final UserMapper userMapper;

    public AuthService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

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
}
