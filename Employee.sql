CREATE TABLE employee (
                          id BIGSERIAL NOT NULL PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          gender VARCHAR(6) NOT NULL,
                          age INT NOT NULL
);

INSERT INTO employee (
    first_name, last_name, gender, age)
VALUES ('Юля', 'Скрафимовна', 'жен', 48);

INSERT INTO employee (
    first_name, last_name, gender, age)
VALUES ('Владимир', 'Александрович', 'муж', 38);

INSERT INTO employee (
    first_name, last_name, gender, age)
VALUES ('Сергей', 'Павлович', 'муж', 54);

SELECT * FROM employee;

UPDATE employee SET age=39 WHERE id=2;

SELECT * FROM employee;

DELETE FROM employee WHERE id=3;

SELECT * FROM employee;

INSERT INTO employee (
    first_name, last_name, gender, age)
VALUES ('Лука', 'Лукич', 'муж', 69);

SELECT first_name, last_name FROM employee WHERE age>30 AND age<50;
SELECT first_name, last_name FROM employee WHERE age<30 OR age>50;

SELECT last_name AS фамилия FROM employee ORDER BY фамилия DESC;

SELECT first_name AS имя FROM employee WHERE LENGTH(first_name)>4;

DELETE FROM employee WHERE id=1;

INSERT INTO employee (
    first_name, last_name, gender, age)
VALUES ('Лада', 'Васильевна', 'жен', 46);


DELETE FROM employee WHERE id=4;

INSERT INTO employee (
    first_name, last_name, gender, age)
VALUES ('Евгений', 'Васильевич', 'муж', 25);

SELECT*FROM employee;

SELECT first_name AS имя, MIN (age) AS минимальный_возраст FROM employee GROUP BY имя;

SELECT first_name AS имя, MAX (age) AS максимальный_возраст FROM employee GROUP BY имя HAVING COUNT(first_name)>1 ORDER BY максимальный_возраст ASC;

CREATE TABLE city (
                      id BIGSERIAL NOT NULL PRIMARY KEY,
                      city_name VARCHAR(100) NOT NULL DEFAULT 'Неизвестно'
);

ALTER TABLE employee
    ADD city_id INT;

ALTER TABLE employee
    ADD FOREIGN KEY (city_id) REFERENCES city(id);

INSERT INTO city (city_name)
VALUES ('Москва');

INSERT INTO city (city_name)
VALUES ('САНКТ-ПЕТЕРБУРГ');

INSERT INTO city (city_name)
VALUES ('Казань');

UPDATE employee SET city_id=1 WHERE id>2;

UPDATE employee SET city_id=2 WHERE id=7;

SELECT first_name AS имя, last_name AS фамилия, city_name AS название
FROM employee
         LEFT JOIN city
                   oN employee.city_id=city.id;

SELECT city_name AS название, first_name AS имя, last_name AS фамилия
FROM employee
         RIGHT JOIN city
                    oN employee.city_id=city.id;

SELECT first_name, last_name, city_name AS название
FROM employee
         FULL JOIN city
                   oN employee.city_id=city.id;

SELECT first_name AS имечко, city_name AS название_города
FROM employee
         CROSS JOIN city;