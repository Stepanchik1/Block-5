package com.example.databases;

import model.City;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CityDAOImpl implements CityDAO {

    @Override
    public void removeCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }

    @Override
    public void changeCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }
    }

    @Override
    public City findById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(City.class, id);
    }
}
}

