package com.example.databases;

import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;

public class EmployeeDAOImpl implements EmployeeDAO {

    public Employee createEmployee(int id) {
        System.out.println("Введите имя сотрудника: ");
        Scanner scanner1 = new Scanner(System.in);
        String name = scanner1.nextLine();
        System.out.println("Введите фамилию сотрудника: ");
        Scanner scanner2 = new Scanner(System.in);
        String lastName = scanner2.nextLine();
        int gender = 0;
        for (; ; ) {
            System.out.println("Выберие пол сотрудника: 1-муж, 2-жен ");
            Scanner scanner3 = new Scanner(System.in);
            try {
                gender = scanner3.nextInt();
                if (gender != 1 && gender != 2) {
                    throw new NumberFormatException();
                } else {
                    break;
                }
            } catch (NumberFormatException | InputMismatchException num) {
                System.out.println("Выберите только либо 1, либо 2");
            }
        }
        String genderString = switch (gender) {
            case 1 -> "муж";
            case 2 -> "жен";
            default -> null;
        };
        int age = 0;
        for (; ; ) {
            System.out.println("Введите возраст сотрудника: ");
            Scanner scanner4 = new Scanner(System.in);
            try {
                age = scanner4.nextInt();
                break;
            } catch (NumberFormatException | InputMismatchException num) {
                System.out.println("Введите число");
            }
        }
        int cityID = 0;
        for (; ; ) {
            System.out.println("Введите индекс города сотрудника: ");
            Scanner scanner5 = new Scanner(System.in);
            try {
                cityID = scanner5.nextInt();
                break;
            } catch (NumberFormatException | InputMismatchException num) {
                System.out.println("Введите число");
            }
        }
        Employee employee = new Employee(id, name, lastName, genderString, age, cityID);
        return employee;
    }

    public Employee createEmployee() {
        return createEmployee(0);
    }

    @Override
    public void addEmployee(Connection connection) throws SQLException {
        Employee employee = createEmployee();
        if (employee.getCityID() != 0) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO employee (\n" +
                    "first_name, last_name, gender, age, city_id) \n" +
                    "VALUES (" + employee.toDataBase() + ");");
            int resultSet = statement.executeUpdate();
            System.out.println(resultSet);
        } else {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO employee (\n" +
                    "first_name, last_name, gender, age) \n" +
                    "VALUES (" + employee.toDataBase() + ");");
            int resultSet = statement.executeUpdate();
            System.out.println(resultSet);
        }

        System.out.println("Сотрудник успешно добавлен:");

        int id = getMaxID(connection);
        findEmployee(connection, id);
    }

    public int getMaxID(Connection connection) throws SQLException {
        PreparedStatement idIndeficator = connection.prepareStatement("SELECT MAX (id) FROM employee;");
        final ResultSet resultID = idIndeficator.executeQuery();
        int id = 0;
        while (resultID.next()) {
            id = resultID.getInt(1);
        }
        return id;
    }

    @Override
    public void deleteEmployee(Connection connection, int id) throws SQLException {
        System.out.println("Сотрудник:");
        findEmployee(connection, id);
        PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE id=(?);");
        statement.setInt(1, id);
        int resultSet = statement.executeUpdate();
        System.out.println("Успешно удален");
        System.out.println(resultSet);
    }

    @Override
    public void updateEmployee(Connection connection, int id) throws SQLException {
        Employee employee = createEmployee(id);
        PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)");
        statement1.setInt(1, id);
        final ResultSet resultSet1 = statement1.executeQuery();
        System.out.println("Старый сотрудник:");
        findEmployee(connection, id);

        PreparedStatement statement2 = connection.prepareStatement("UPDATE employee SET first_name=(?), last_name=(?), gender = (?), age=(?), city_id=(?) WHERE id=(?);");
        statement2.setString(1, employee.getFirstName());
        statement2.setString(2, employee.getLastName());
        statement2.setString(3, employee.getGender());
        statement2.setInt(4, employee.getAge());
        statement2.setInt(5, employee.getCityID());
        statement2.setInt(6, id);
        final int resultSet2 = statement2.executeUpdate();
        System.out.println(resultSet2);

        PreparedStatement statement3 = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)");
        statement3.setInt(1, id);
        final ResultSet resultSet3 = statement3.executeQuery();

        System.out.println("Сотрудник успешно изменен:");
        System.out.println("Новый сотрудник:");
        findEmployee(connection, id);
    }

    @Override
    public boolean findEmployee(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)");
        statement.setInt(1, id);
        final ResultSet resultSet = statement.executeQuery();
        String firstName = "";
        String lastName = "";
        String gender = "";
        int age = 0;
        int cityId = 0;
        while (resultSet.next()) {
            firstName = resultSet.getString("first_name");
            lastName = resultSet.getString("last_name");
            gender = resultSet.getString("gender");
            age = resultSet.getInt(5);
            cityId = resultSet.getInt(6);
        }
        Employee employee = new Employee(id, firstName, lastName, gender, age, cityId);
        if (firstName.isEmpty() && lastName.isEmpty() && gender.isEmpty()) {
            employee = null;
            System.out.println("Сотрудника с данным id нет");
            return false;
        }
        System.out.println(employee);
        return true;
    }

    @Override
    public void chooseEmployee(Connection connection, int id) throws SQLException {
        if (findEmployee(connection, id)) {
            System.out.println("Вы выбрали данного сотрудника");
        }
    }

    @Override
    public void showAllEmployees(Connection connection) throws SQLException {
        System.out.println("Список всех сотрудников:");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee");
        final ResultSet resultSet = statement.executeQuery();
        int id = 0;
        String firstName = "";
        String lastName = "";
        String gender = "";
        int age = 0;
        int cityId = 0;
        while (resultSet.next()) {
            id = resultSet.getInt(1);
            firstName = resultSet.getString("first_name");
            lastName = resultSet.getString("last_name");
            gender = resultSet.getString("gender");
            age = resultSet.getInt(5);
            cityId = resultSet.getInt(6);
            Employee employee = new Employee(id, firstName, lastName, gender, age, cityId);
            if (firstName.isEmpty() && lastName.isEmpty() && gender.isEmpty()) {
                employee = null;
            }
            if (employee != null) {
                System.out.println(employee);
            }
        }
    }
}