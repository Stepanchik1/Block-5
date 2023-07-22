package com.example.databases;

import model.City;

public interface CityDAO {

    void removeCity (City city);
    void changeCity (City city);
    City findById (int id);

}
