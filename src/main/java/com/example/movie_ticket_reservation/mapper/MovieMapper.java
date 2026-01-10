package com.example.movie_ticket_reservation.mapper;

import com.example.movie_ticket_reservation.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MovieMapper {
    // 获取所有上映中电影
    List<Movie> getAllMovies();

    // 根据关键字模糊查询
    List<Movie> searchMovies(@Param("keyword") String keyword);

    Movie getMovieById(@Param("id") Long id);
}
