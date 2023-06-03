package com.example.databases;

import model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.Scanner;

@SpringBootApplication
public class DataBasesApplication {

    final static String user = "postgres";
    final static String password = "1234";
    final static String URL = "jdbc:postgresql://localhost:5432/skypro";

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(DataBasesApplication.class, args);

        final Connection connection = DriverManager.getConnection(URL, user, password);

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)");

        hometask1(statement);

        EmployeeDAO employeeDAO = new EmployeeDAO();

        for (int i = 1; i <= 10; i++) {
            employeeDAO.saveEmployee(statement, 1, i);
        }

        for (; ; ) {
            System.out.println("Для выхода нажмите 0");
            System.out.println("Чтобы получить полный список сотрудников введите %");
            System.out.println("Введите id сотрудника чтобы выбрать его:");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if (s.trim().equals("%")) {
                employeeDAO.showAllEmployees();
            }
            try {
                int id = Integer.parseInt(s.trim());
                if (id == 0) {
                    return;
                }
                employeeDAO.chooseEmployee(id);
                action(id, employeeDAO);
            } catch (NumberFormatException n) {
                System.out.println("Неверно введен формат id");
            }
        }
    }

    static void hometask1(PreparedStatement statement) throws SQLException {
        statement.setInt(1, 6);

        final ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String firstName = "имя: " + resultSet.getString("first_name");
            String lastName = "фамилия: " + resultSet.getString("last_name");
            String gender = "пол: " + resultSet.getString("gender");
            int age = resultSet.getInt(5);
            int cityId = resultSet.getInt(6);

            // Выводим данные в консоль
            System.out.println("id-" + id);
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(gender);
            System.out.println("возраст: " + age);
            System.out.println("индекс города - " + cityId);
        }
    }

    private static void action(int id, EmployeeDAO employeeDAO) {
        for (; ; ) {
            System.out.println("Выберите действие: \n1 - изменить информацию о сотруднике, \n2 - удалить сотрудника, \n0 - ничего не делать");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            try {
                int ch = Integer.parseInt(s.trim());
                if (ch == 0) return;
                if (ch == 1) {
                    employeeDAO.updateEmployee(id);
                    return;
                }
                if (ch == 2) {
                    employeeDAO.deleteEmployee(id);
                    return;
                }
            } catch (NumberFormatException ignored) {
            }
        }
    }
}