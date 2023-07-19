package model;

import javax.persistence.*;

@Entity
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    public int id;
    @Column (name = "name")
    public String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public City() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "город " + name + " (id №"+id+")";
    }
}
