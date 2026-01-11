package com.example.movie_ticket_reservation.mapper;

import com.example.movie_ticket_reservation.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User selectByUsername(@Param("username") String username);

    int insertUser(User user);

    User selectById(Long userId);
}
