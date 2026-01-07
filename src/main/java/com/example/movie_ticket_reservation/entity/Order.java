package com.example.movie_ticket_reservation.entity;

import java.time.LocalDateTime;

public class Order {

    private Long id;

    // 下单用户
    private Long userId;

    // 场次
    private Long screeningId;

    // 座位ID（第一阶段：单座）
    private Long seatId;

    // 订单价格（元）
    private Integer price;

    /**
     * 订单状态
     * 0 = 未支付
     * 1 = 已支付
     * 2 = 已取消
     */
    private Integer status;

    // 下单时间
    private LocalDateTime createTime;

    // ===== getter / setter =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Long screeningId) {
        this.screeningId = screeningId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
}
