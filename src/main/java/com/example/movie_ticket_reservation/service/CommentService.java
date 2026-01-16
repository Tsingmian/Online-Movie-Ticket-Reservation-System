package com.example.movie_ticket_reservation.service;

import com.example.movie_ticket_reservation.entity.Comment;
import com.example.movie_ticket_reservation.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public void addComment(Long movieId, String userName, String content) {
        commentMapper.addComment(movieId, userName, content);
    }

    public List<Comment> getCommentsByMovieId(Long movieId) {
        return commentMapper.getCommentsByMovieId(movieId);
    }
}