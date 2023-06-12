package com.example.databases;

import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.TreeMap;

public interface EmployeeDAO {

    abstract void addEmployee(Connection connection) throws SQLException;

    abstract int getMaxID (Connection connection) throws SQLException;

        abstract void deleteEmployee(Connection connection, int id) throws SQLException;

        abstract void updateEmployee(Connection connection, int id) throws SQLException;

        abstract boolean findEmployee(Connection connection, int id) throws SQLException;

        abstract void chooseEmployee(Connection connection, int id) throws SQLException;

        abstract void showAllEmployees(Connection connection) throws SQLException;
        abstract TreeMap <Integer, Employee> getListOfEmployees (Connection connection) throws SQLException;

    abstract Employee getEmployee (Connection connection, int id) throws SQLException;
    }

