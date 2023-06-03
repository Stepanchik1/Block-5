package model;

public class Employee {
    private final int id;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private int cityID;

    public Employee(int id, String firstName, String lastName, String gender, int age, int cityID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.cityID = cityID;
    }

    public Employee(int id, String firstName, String lastName, String gender, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    @Override
    public String toString() {
        return "Работник id № " + id + ": " +
                firstName + " " +
                lastName +
                ", пол - " + gender +
                ", возраст - " + age +
                ", индекс города - " + cityID
                ;
    }
}
