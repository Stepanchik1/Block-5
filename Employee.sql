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