package com.example.movie_ticket_reservation.mapper;

import com.example.movie_ticket_reservation.entity.Comment; // ← 正确导入
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> getCommentsByMovieId(@Param("movieId") Long movieId);

    void addComment(@Param("movieId") Long movieId,
                    @Param("userName") String userName,
                    @Param("content") String content);
}