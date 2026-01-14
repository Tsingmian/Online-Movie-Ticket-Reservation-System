package com.example.movie_ticket_reservation.mapper;

import com.example.movie_ticket_reservation.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MovieMapper {

    // 普通用户：获取所有上映中的电影
    List<Movie> getAllMoviesAvailable();

    // 管理端：获取所有电影
    List<Movie> getAllMovies();

    // 根据关键字模糊查询电影
    List<Movie> searchMovies(@Param("keyword") String keyword);

    // ID获取电影详情
    Movie getMovieById(@Param("id") Long id);

    // 新增电影（管理端）
    void insertMovie(Movie movie);

    // 更新电影（管理端）
    void updateMovie(Movie movie);

    // 删除电影（管理端）
    void deleteMovie(@Param("id") Long id);

    Movie selectById(Long movieId);
}
