package com.example.databases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class DataBasesApplication {

    final static String user = "postgres";
    final static String password = "1234";
    final static String URL = "jdbc:postgresql://localhost:5432/skypro";

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(DataBasesApplication.class, args);

        final Connection connection = DriverManager.getConnection(URL, user, password);

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

            // Выводим данные в консоль
            System.out.println("id-"+id);
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(gender);
            System.out.println("возраст: " + age);
            System.out.println("индекс города - "+cityId);
        }
    }
}