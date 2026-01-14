package com.example.movie_ticket_reservation.controller;

import com.example.movie_ticket_reservation.entity.Comment;
import com.example.movie_ticket_reservation.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public Result<String> addComment(
            @RequestParam Long movieId,
            @RequestParam String userName,
            @RequestParam String content) {
        commentService.addComment(movieId, userName, content);
        return Result.success("评论添加成功！");
    }

    @GetMapping("/list")
    public Result<List<Comment>> getComments(@RequestParam Long movieId) {
        List<Comment> comments = commentService.getCommentsByMovieId(movieId);
        return Result.success(comments);
    }

    // 统一响应结构
    public static class Result<T> {
        private int code;
        private String message;
        private T data;

        public static <T> Result<T> success(T data) {
            Result<T> result = new Result<>();
            result.code = 200;
            result.message = "success";
            result.data = data;
            return result;
        }

        // getter/setter
        public int getCode() { return code; }
        public void setCode(int code) { this.code = code; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public T getData() { return data; }
        public void setData(T data) { this.data = data; }
    }

    @GetMapping("/test")
    public String test() {
        return "Controller is working!";
    }
}