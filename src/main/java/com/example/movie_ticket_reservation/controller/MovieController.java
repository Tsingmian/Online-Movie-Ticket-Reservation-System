package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.entity.Movie;
import com.example.movie_ticket_reservation.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public Response getMovies(@RequestParam(value = "keyword", required = false) String keyword) {
        List<Movie> movies;
        if (keyword == null || keyword.isEmpty()) {
            movies = movieService.getAllMovies();
        } else {
            movies = movieService.searchMovies(keyword);
        }
        return new Response(1, movies);
    }

    @GetMapping("/{id}")
    public Response getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return new Response(1, movie);
    }


    static class Response {
        private int status;
        private Object data;
        public Response(int status, Object data) {
            this.status = status;
            this.data = data;
        }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public Object getData() { return data; }
        public void setData(Object data) { this.data = data; }
    }
}
