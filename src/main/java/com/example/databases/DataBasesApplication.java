package com.example.databases;

import model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.Scanner;
import java.util.TreeMap;

@SpringBootApplication
public class DataBasesApplication {

    final static String user = "postgres";
    final static String password = "1234";
    final static String URL = "jdbc:postgresql://localhost:5432/skypro";

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(DataBasesApplication.class, args);

        final Connection connection = DriverManager.getConnection(URL, user, password);

        EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();

        //создаю пробный объект, чтоб протестить метод

        Employee employee = new Employee(-200, "g", "f", "u", 23, 5);
        employeeDAOImpl.changeEmployee(employee);  //на этой строке ошибка

        //*****
Scanner sc = new Scanner(System.in);
String gggg = sc.nextLine(); //добавил чтоб программа прерывалась на этом месте

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

    private static void action(Connection connection, int id, EmployeeDAOImpl employeeDAOImpl) {
        for (; ; ) {
            System.out.println("Выберите действие: \n1 - изменить информацию о сотруднике, \n2 - удалить сотрудника, \n0 - ничего не делать");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            try {
                int ch = Integer.parseInt(s.trim());
                if (ch == 0) return;
      //          if (ch == -1) {employeeDAOImpl.changeEmployee();}
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