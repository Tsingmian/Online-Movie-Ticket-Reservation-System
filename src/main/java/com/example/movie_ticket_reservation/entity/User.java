package com.example.movie_ticket_reservation.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class User {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String username;
    // getter / setter

    //    public String getPassword() { return password; }
    //    public void setPassword(String password) { this.password = password; }

//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRole() {
//        return role;
//    }
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public LocalDateTime getCreateTime() {
//        return createTime;
//    }
//    public void setCreateTime(LocalDateTime createTime) {
//        this.createTime = createTime;
//    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }


    @Setter
    @Getter

    private String password;
    private String role;   // USER / ADMIN
    private Integer status;

    private LocalDateTime createTime;


}
