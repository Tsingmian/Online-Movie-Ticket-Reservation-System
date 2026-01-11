package com.example.movie_ticket_reservation.mapper;

import com.example.movie_ticket_reservation.entity.Screening;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScreeningMapper {

    Screening selectById(@Param("id") Long id);

    List<Screening> selectByMovieId(@Param("movieId") Long movieId);

    List<Screening> selectAll();

    void insert(Screening screening);

    void update(Screening screening);

    void delete(Long id);
}

