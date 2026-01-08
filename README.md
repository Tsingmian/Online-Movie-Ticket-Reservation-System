# Online-Movie-Ticket-Reservation-System （Spring Boot + Vue）
This online movie ticket booking system offers role-based access: end users can browse movies, check showtimes/inventory, book/cancel tickets, post reviews; administrators have full CRUD permissions for cinemas, showtimes, movies, and manage ticket inventory.

## 一、项目简介

本项目是一个基于 **Spring Boot + Vue** 的电影购票系统，主要实现电影信息展示、场次管理、选座购票以及评论互动等功能。  
后端采用 **SQL 驱动的数据访问方式**，在保证 SQL 可控性的前提下，兼顾系统的可维护性与并发安全性。

项目为 **Java课程设计 / 实训项目 / 后端学习实践项目**。

---

## 二、技术栈

### 后端

- Java 17
- Spring Boot 3.x
- MyBatis（主要数据访问）
- JDBC （少部分或者无）
- MySQL (SQLyog/Laragon/DataGrip)
- Maven

### 前端

- Vue 3
- Axios (待定)
- Element Plus

---

## 三、系统功能

### 1. 电影模块
- 电影列表展示
- 电影详情查看
- 上映 / 下架状态管理
- 管理员自由增改

### 2. 场次（Screening）模块
- 按电影查询场次
- 显示放映时间、影厅、票价、折扣

### 3. 座位模块
- 按场次加载座位
- 已售 / 可选状态区分
- 下单时锁定座位（防止并发超卖）

### 4. 订单模块
- 创建订单
- 订单状态管理
- 已购座位记录
- 订单修改

### 5. 评论模块
- 电影评论
- 二级评论（回复）
- 点赞 / 点踩（每人一次）

---

## 四、系统架构说明

```text
前端（Vue）
   ↓ Axios
Controller（REST API）
   ↓
Service（业务逻辑 & 事务）
   ↓
DAO 层
 ├─ MyBatis Mapper（常规 CRUD）
 └─ JDBC DAO（并发控制、锁座）
   ↓
MySQL
