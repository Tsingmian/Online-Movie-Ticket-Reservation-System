package com.example.movie_ticket_reservation.mapper;

import com.example.movie_ticket_reservation.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    int insertOrder(Order order);
}
