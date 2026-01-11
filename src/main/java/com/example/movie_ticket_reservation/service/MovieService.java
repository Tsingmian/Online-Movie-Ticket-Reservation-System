package com.example.movie_ticket_reservation.service;

import com.example.movie_ticket_reservation.entity.Movie;
import com.example.movie_ticket_reservation.mapper.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieMapper movieMapper;

    public MovieService(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    // 普通用户查询所有上映中电影
    public List<Movie> getAllMovies() {
        return movieMapper.getAllMoviesAvailable(); // 只返回 status = 1 的电影
    }

    // 管理端查询所有电影（包括下架的）
    public List<Movie> getAllMoviesAdmin() {
        return movieMapper.getAllMovies(); // status 不限制
    }

    public List<Movie> searchMovies(String keyword) {
        return movieMapper.searchMovies(keyword);
    }

    public Movie getMovieById(Long id) {
        return movieMapper.getMovieById(id);
    }

    // 新增电影（管理端）
    public void addMovie(Movie movie) {
        movieMapper.insertMovie(movie);
    }

    // 更新电影（管理端）
    public void updateMovie(Movie movie) {
        movieMapper.updateMovie(movie);
    }

    // 删除电影（管理端）
    public void deleteMovie(Long id) {
        movieMapper.deleteMovie(id);
    }
}
