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

    public List<Movie> getAllMovies() {
        return movieMapper.getAllMovies();
    }

    public List<Movie> searchMovies(String keyword) {
        return movieMapper.searchMovies(keyword);
    }

    public Movie getMovieById(Long id) {
        return movieMapper.getMovieById(id);
    }
}
