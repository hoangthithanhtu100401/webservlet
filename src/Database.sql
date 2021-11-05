CREATE DATABASE factory;
USE factory;

CREATE TABLE employees (
    Id TINYINT AUTO_INCREMENT PRIMARY KEY,
    Age TINYINT,
    Name NVARCHAR(30),
    City NVARCHAR(30)
);

INSERT INTO employees(Age, Name, City) VALUES (20, 'Đan', 'Nam Định'),
                                                  (30, 'Khánh', 'Hưng Yên'),
                                                  (20, 'Hậu', 'Hải Phòng'),
                                                  (20, 'Tú', 'Hà Nội');
SELECT COUNT(Id)
FROM employees