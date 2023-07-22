package com.example.databases;

import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.TreeMap;

public interface EmployeeDAO {

    void addEmployee(Connection connection) throws SQLException;

    int getMaxID(Connection connection) throws SQLException;

    void deleteEmployee(Connection connection, int id) throws SQLException;

    void updateEmployee(Connection connection, int id) throws SQLException;

    boolean findEmployee(Connection connection, int id) throws SQLException;

    void chooseEmployee(Connection connection, int id) throws SQLException;

    void showAllEmployees(Connection connection) throws SQLException;

    TreeMap<Integer, Employee> getListOfEmployees(Connection connection) throws SQLException;

    Employee getEmployee(Connection connection, int id) throws SQLException;

    // скорректированные методы из домашки по entity/hibernate

    void removeEmployee(Employee employee);

    void changeEmployee(Employee employee);

    Employee findById(int id);

}

