package com.example.databases;

import model.City;
import model.Employee;

import java.sql.*;
import java.util.Scanner;
import java.util.TreeMap;

public class DataBasesApplication {

    final static String user = "postgres";
    final static String password = "1234";
    final static String URL = "jdbc:postgresql://localhost:5432/skypro";

    public static void main(String[] args) throws SQLException {
        //SpringApplication.run(DataBasesApplication.class, args);

        final Connection connection = DriverManager.getConnection(URL, user, password);

        EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();

        CityDAOImpl cityDAOimpl = new CityDAOImpl();

        //собсна проверяю, что хибернейт работает
        City newCity = cityDAOimpl.findById(1);
        System.out.println(newCity);
        Employee employee = employeeDAOImpl.findById(5);
        System.out.println(employee);

        //другая часть дз

        entity(employeeDAOImpl);

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

    public static void entity(EmployeeDAOImpl employeeDAOImp) {
        for (; ; ) {
            Employee newEmployee = null;
            try {
                System.out.println("Введите id сотрудника или 0, чтобы выйти");
                Scanner scanner = new Scanner(System.in);
                int id = scanner.nextInt();
                if (id == 0) {
                    return;
                }
                newEmployee = employeeDAOImp.findById(id);
                System.out.println("Выбран " + newEmployee);
                System.out.println("Введите новый возраст сотрудника");
                int age = scanner.nextInt();
                newEmployee.setAge(age);
                employeeDAOImp.changeEmployee(newEmployee);
                System.out.println("Изменено:" + employeeDAOImp.findById(id));
                for (; ; ) {
                    System.out.println("Введите id сотрудника, которого надо удалить или нажмите 0, чтобы вернуться назад");
                    int idToRemove = scanner.nextInt();
                    if (idToRemove == 0) {
                        break;
                    }
                    newEmployee = employeeDAOImp.findById(idToRemove);
                    if (newEmployee != null) {
                        employeeDAOImp.removeEmployee(newEmployee);
                        if (employeeDAOImp.findById(idToRemove) == null) {
                            System.out.println("Удален " + newEmployee);
                            break;
                        } else {
                            System.out.println("Не удалось удалить");
                        }
                        break;
                    } else {
                        System.out.println("Сотрудник с таким id не найден");
                    }
                }
            } catch (NullPointerException error) {
                System.out.println("Ошибка связи с БД");
            }
        }
    }
}

