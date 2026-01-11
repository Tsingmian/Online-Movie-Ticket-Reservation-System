package com.example.movie_ticket_reservation.service;

import com.example.movie_ticket_reservation.entity.Screening;
import com.example.movie_ticket_reservation.entity.Seat;
import com.example.movie_ticket_reservation.mapper.ScreeningMapper;
import com.example.movie_ticket_reservation.mapper.SeatMapper;
import org.springframework.stereotype.Service;
import com.example.movie_ticket_reservation.entity.Movie;
import com.example.movie_ticket_reservation.mapper.MovieMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class ScreeningService {

    private final ScreeningMapper screeningMapper;
    private final SeatMapper seatMapper;
    private final MovieMapper movieMapper;

    public ScreeningService(ScreeningMapper screeningMapper,
                            SeatMapper seatMapper,
                            MovieMapper movieMapper) {
        this.screeningMapper = screeningMapper;
        this.seatMapper = seatMapper;
        this.movieMapper = movieMapper;
    }

    // 根据电影查场次（用户和管理端共用）
    public List<Screening> getByMovieId(Long movieId) {
        return screeningMapper.selectByMovieId(movieId);
    }

    // 查询单个场次
    public Screening getById(Long id) {
        return screeningMapper.selectById(id);
    }

    // 查询所有场次（管理端使用）
    public List<Screening> getAllScreenings() {
        return screeningMapper.selectAll();
    }

    // 新增场次（管理端）
//    @Transactional
//    public void addScreening(Screening screening) {
//        screeningMapper.insert(screening);
//        if (screening.getMovieId() == null) {
//            throw new IllegalArgumentException("电影ID不能为空");
//        }
//        if (screening.getStartTime() == null) {
//            throw new IllegalArgumentException("开始时间不能为空");
//        }
//        int rows = 5; // 默认行数，可自定义
//        int cols = 5; // 默认列数
//        for (int r = 1; r <= rows; r++) {
//            for (int c = 1; c <= cols; c++) {
//                Seat seat = new Seat();
//                seat.setScreeningId(screening.getId());
//                seat.setRowNum(r);
//                seat.setColNum(c);
//                seat.setStatus(0); // 空闲
//                seatMapper.insert(seat);
//            }
//        }
//    }

    @Transactional // 推荐：保证场次和座位要么都成功，要么都失败
    public void addScreening(Screening screening) {
        // 1. 校验必要字段
        if (screening.getMovieId() == null) {
            throw new IllegalArgumentException("电影ID不能为空");
        }
        if (screening.getStartTime() == null) {
            throw new IllegalArgumentException("开始时间不能为空");
        }

        // 2. 查询电影获取时长
        Movie movie = movieMapper.selectById(screening.getMovieId());
        if (movie == null || movie.getDuration() == null) {
            throw new IllegalArgumentException("电影不存在或未设置时长");
        }

        // 3. 自动计算 endTime
        LocalDateTime endTime = screening.getStartTime().plusMinutes(movie.getDuration());
        screening.setEndTime(endTime);

        // 4. 设置默认状态（可选）
        if (screening.getStatus() == null) {
            screening.setStatus(1); // 1=可售
        }

        // 5. 插入场次
        screeningMapper.insert(screening);

        // 6. 生成座位（使用新插入的 screening.getId()）
        generateSeats(screening.getId());
    }

    // 把生成座位逻辑抽出来（更清晰）
    private void generateSeats(Long screeningId) {
        int rows = 5;
        int cols = 5;
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                Seat seat = new Seat();
                seat.setScreeningId(screeningId);
                seat.setRowNum(r);
                seat.setColNum(c);
                seat.setStatus(0); // 0=空闲
                seatMapper.insert(seat);
            }
        }
    }

    // 更新场次
//    public void updateScreening(Screening screening) {
//        screeningMapper.update(screening);
//    }
    @Transactional
    public void updateScreening(Screening screening) {
        if (screening.getMovieId() == null || screening.getStartTime() == null) {
            throw new IllegalArgumentException("电影ID和开始时间不能为空");
        }

        Movie movie = movieMapper.selectById(screening.getMovieId());
        if (movie == null || movie.getDuration() == null) {
            throw new IllegalArgumentException("电影不存在或未设置时长");
        }

        // 重新计算 endTime
        screening.setEndTime(screening.getStartTime().plusMinutes(movie.getDuration()));

        screeningMapper.update(screening);
    }

    // 删除场次
    public void deleteScreening(Long id) {
        seatMapper.selectByScreeningId(id);
        screeningMapper.delete(id);
        // 删除座位：Seat 表有外键 ON DELETE CASCADE 可以自动删除
    }

    // 根据场次获取座位（用户和管理端共用）
    public List<Seat> getSeatsByScreening(Long screeningId) {
        return seatMapper.selectByScreeningId(screeningId);
    }

    public void updateSeats(Long screeningId, List<Seat> seats) {
        for (Seat seat : seats) {
            // 确保 seat 属于该 screeningId（防篡改）
            if (!screeningId.equals(seat.getScreeningId())) {
                throw new IllegalArgumentException("Seat does not belong to this screening");
            }
            seatMapper.update(seat); // 需要实现 update 方法
        }
    }
}