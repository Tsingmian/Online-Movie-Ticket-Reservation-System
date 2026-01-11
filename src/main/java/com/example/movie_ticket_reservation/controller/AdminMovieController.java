package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.entity.Movie;
import com.example.movie_ticket_reservation.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/movies") // 管理端接口前缀
@CrossOrigin(origins = "http://localhost:5173")
public class AdminMovieController {

    private final MovieService movieService;

    public AdminMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // 查询所有电影（可搜索）
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

    // 查询单个电影
    @GetMapping("/{id}")
    public Response getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return new Response(1, movie);
    }

    // 新增电影
    @PostMapping
    public Response addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new Response(1, "新增成功");
    }

    // 更新电影
    @PutMapping("/{id}")
    public Response updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        movie.setId(id);
        movieService.updateMovie(movie);
        return new Response(1, "更新成功");
    }

    // 删除电影
    @DeleteMapping("/{id}")
    public Response deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return new Response(1, "删除成功");
    }

    // 响应类
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
