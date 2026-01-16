package com.example.movie_ticket_reservation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data   // 可自动生成 getter / setter / toString
public class Comment {

    private Long commentId;
    private Long movieId;
    private String userName;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commentTime;
}
