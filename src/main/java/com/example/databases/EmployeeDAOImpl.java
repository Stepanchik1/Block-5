package com.example.databases;

        import model.Employee;

        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.Scanner;
        import java.util.TreeMap;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final TreeMap<Integer, Employee> mapOfEmployees = new TreeMap<>();

    public Employee downloadEmployee(PreparedStatement statement, int index, int id) throws SQLException {
        statement.setInt(index, id);
        final ResultSet resultSet = statement.executeQuery();
        String firstName = "";
        String lastName = "";
        String gender = "";
        int age = 0;
        int cityId = 0;
        while (resultSet.next()) {
            //int id = resultSet.getInt(1);
            firstName = resultSet.getString("first_name");
            lastName = resultSet.getString("last_name");
            gender = resultSet.getString("gender");
            age = resultSet.getInt(5);
            cityId = resultSet.getInt(6);
        }
        if (firstName.isEmpty() && lastName.isEmpty() && gender.isEmpty() && age == 0 && cityId == 0) {
            return null;
        }
        return new Employee(id, firstName, lastName, gender, age, cityId);
    }

    public Employee createEmployee(int id) {
        System.out.println("Введите имя сотрудника: ");
        Scanner scanner1 = new Scanner(System.in);
        String name = scanner1.nextLine();
        System.out.println("Введите фамилию сотрудника: ");
        Scanner scanner2 = new Scanner(System.in);
        String lastName = scanner2.nextLine();
        System.out.println("Выберие пол сотрудника: 1-муж, 2-жен ");
        Scanner scanner3 = new Scanner(System.in);
        int gender = scanner3.nextInt();
        String genderString = switch (gender) {
            case 1 -> "муж";
            case 2 -> "жен";
            default -> null;
        };
        System.out.println("Введите возраст сотрудника: ");
        Scanner scanner4 = new Scanner(System.in);
        int age = scanner3.nextInt();
        System.out.println("Введите индекс города сотрудника: ");
        Scanner scanner5 = new Scanner(System.in);
        int cityID = scanner5.nextInt();
        Employee employee = new Employee(id, name, lastName, genderString, age, cityID);
        System.out.println(employee);
        return employee;
    }


    @Override
    public void deleteEmployee(int id) {
        if (mapOfEmployees.containsKey(id)) {
            System.out.println(mapOfEmployees.get(id) + " УДАЛЕН");
            mapOfEmployees.remove(id);
        }
    }

    @Override
    public void updateEmployee(int id) {
        Employee employee = createEmployee(id);
        System.out.println("Сотрудник успешно изменен:");
        System.out.println("Старый сотрудник:" + mapOfEmployees.get(id).toString().replace("Работник", ""));
        mapOfEmployees.put(id, employee);
        System.out.println("Новый сотрудник:" + mapOfEmployees.get(id).toString().replace("Работник", ""));
    }

    @Override
    public void findEmployee(int id) {
        if (mapOfEmployees.containsKey(id)) {
            System.out.println(mapOfEmployees.get(id));
        } else {
            System.out.println("Сотрудник с таким id не найден");
        }
    }

    @Override
    public Employee chooseEmployee(int id) {
        if (mapOfEmployees.containsKey(id)) {
            System.out.println("Выбран сотрудник:" + mapOfEmployees.get(id).toString().replace("Работник", ""));
            return mapOfEmployees.get(id);
        } else {
            System.out.println("Сотрудник с таким id не найден");
            return null;
        }
    }

    @Override
    public void showAllEmployees() {
        System.out.println("Список всех сотрудников:");
        for (Employee employee : mapOfEmployees.values()) {
            System.out.println(employee);
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        if (employee != null) {
            if (mapOfEmployees.get(employee.getId()) == null) {
                mapOfEmployees.put(employee.getId(), employee);
                System.out.println(employee + " - добавлен в базу");
            } else {
                System.out.println("работник c id=" + employee.getId() + " уже есть");
            }
        }
    }

    @Override
    public void saveEmployee(PreparedStatement statement, int index, int id) throws SQLException {
        EmployeeDAO.super.saveEmployee(statement, index, id);
    }


}