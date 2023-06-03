package com.example.databases;

import model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

public interface EmployeeDAOImpl {


    abstract void addEmployee(Employee employee);

    abstract Employee downloadEmployee(PreparedStatement statement, int index, int id) throws SQLException;


    default void saveEmployee(PreparedStatement statement, int index, int id) throws SQLException {
        Employee newEmployee = downloadEmployee(statement, index, id);
        addEmployee(newEmployee);
    }

    abstract void deleteEmployee(int id);

    abstract void updateEmployee(int id);

    abstract void findEmployee(int id);

    abstract Employee chooseEmployee(int id);

    abstract void showAllEmployees();

}
