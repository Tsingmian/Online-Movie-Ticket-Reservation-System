package com.example.movie_ticket_reservation.mapper;

import com.example.movie_ticket_reservation.entity.Screening;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScreeningMapper {

    Screening selectById(@Param("id") Long id);
}
