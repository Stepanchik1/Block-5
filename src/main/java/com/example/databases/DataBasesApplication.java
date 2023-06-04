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

        hometask1(connection);

        EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();

        for (; ; ) {
            System.out.println("Для выхода нажмите 0");
            System.out.println("Чтобы получить полный список сотрудников введите %");
            System.out.println("Чтобы создать нового сотрудника введите =");
            System.out.println("Или введите id сотрудника чтобы найти и выбрать его:");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            int id = 0;
            if (s.trim().equals("%")) {
                employeeDAOImpl.showAllEmployees(connection);
            } else if (s.trim().equals("=")) {
                employeeDAOImpl.addEmployee(connection);
            } else {
                try {
                    id = Integer.parseInt(s.trim());
                    if (id == 0) {
                        return;
                    }
                    employeeDAOImpl.chooseEmployee(connection, id);
                    action(connection, id, employeeDAOImpl);
                } catch (NumberFormatException n) {
                    System.out.println("Неверно введен формат id");
                }
            }
        }
    }

    static void hometask1(Connection connection) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)");
        statement.setInt(1, 6);

        final ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            int id = resultSet.getInt(1);
            String firstName = "имя: " + resultSet.getString("first_name");
            String lastName = "фамилия: " + resultSet.getString("last_name");
            String gender = "пол: " + resultSet.getString("gender");
            int age = resultSet.getInt(5);
            int cityId = resultSet.getInt(6);

            System.out.println("id-" + id);
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(gender);
            System.out.println("возраст: " + age);
            System.out.println("индекс города - " + cityId);
        }
    }

    private static void action(Connection connection, int id, EmployeeDAOImpl employeeDAOImpl) {
        for (; ; ) {
            System.out.println("Выберите действие: \n1 - изменить информацию о сотруднике, \n2 - удалить сотрудника, \n0 - ничего не делать");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            try {
                int ch = Integer.parseInt(s.trim());
                if (ch == 0) return;
                if (ch == 1) {
                    employeeDAOImpl.updateEmployee(connection, id);
                    return;
                }
                if (ch == 2) {
                    employeeDAOImpl.deleteEmployee(connection, id);
                    return;
                }
            } catch (NumberFormatException ignored) {
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}