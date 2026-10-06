-- Run this once against your MySQL server before using the app.
CREATE DATABASE IF NOT EXISTS contactdb;
USE contactdb;

CREATE TABLE IF NOT EXISTS contact (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(50)  NOT NULL,
    email     VARCHAR(100),
    cellphone VARCHAR(20)
);

-- Sample rows matching the exercise's worked example.
INSERT INTO contact (name, email, cellphone) VALUES
    ('ITI',  'iti@mail.com',  '01005628775'),
    ('MCIT', 'mcit@mail.com', '01000000000');
