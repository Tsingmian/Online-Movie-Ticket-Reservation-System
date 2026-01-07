-- 2. 创建数据库
CREATE DATABASE IF NOT EXISTS movie_ticket_system 
DEFAULT CHARACTER SET utf8mb4 
DEFAULT COLLATE utf8mb4_unicode_ci;
-- 3. 使用数据库
USE movie_ticket_system;
-- 4. 创建电影表（我提供的SQL）
CREATE TABLE movies (
    movie_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    director VARCHAR(50),
    actors VARCHAR(200),
    genre VARCHAR(50),
    duration INT,
    release_date DATE,
    LANGUAGE VARCHAR(20),
    DESCRIPTION TEXT,
    rating DECIMAL(3,1),
    price DECIMAL(10,2) NOT NULL,
    poster_url VARCHAR(200),
    STATUS VARCHAR(20) DEFAULT 'COMING',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
-- 5. 创建放映场次表
CREATE TABLE screenings (
    screening_id INT PRIMARY KEY AUTO_INCREMENT,
    movie_id INT NOT NULL,
    theater_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    available_seats INT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id) ON DELETE CASCADE
);
INSERT INTO movies (title, director, actors, genre, duration, release_date, price, STATUS) VALUES
('流浪地球2', '郭帆', '吴京, 刘德华, 李雪健', '科幻', 173, '2023-01-22', 45.00, 'SHOWING'),
('满江红', '张艺谋', '沈腾, 易烊千玺, 张译', '喜剧', 159, '2023-01-22', 40.00, 'SHOWING'),
('深海', '田晓鹏', '苏鑫, 王亭文', '动画', 112, '2023-01-22', 38.00, 'SHOWING'),
('无名', '程耳', '梁朝伟, 王一博, 周迅', '剧情', 128, '2023-01-22', 42.00, 'SHOWING'),
('交换人生', '苏伦', '雷佳音, 张小斐, 张宥浩', '喜剧', 110, '2023-01-22', 35.00, 'SHOWING'),
('蚁人3：量子狂潮', '佩顿·里德', '保罗·路德, 伊万杰琳·莉莉', '科幻', 125, '2023-02-17', 50.00, 'COMING'),
('黑豹2', '瑞恩·库格勒', '利蒂希娅·赖特, 露皮塔·尼永奥', '动作', 162, '2023-02-07', 48.00, 'COMING');
INSERT INTO theaters (theater_name, total_seats) VALUES
('1号激光厅', 120),
('2号巨幕厅', 180),
('3号VIP厅', 60),
('4号杜比厅', 100);
-- 查看数据
SELECT * FROM movies;
SELECT * FROM theaters;
SELECT * FROM screenings;