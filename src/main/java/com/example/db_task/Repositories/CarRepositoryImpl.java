package com.example.db_task.Repositories;

import com.example.db_task.TableClasses.Cars;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class CarRepositoryImpl implements CarRepository{

    @PersistenceContext
    public static EntityManagerFactory entityManagerFactory;
    static {entityManagerFactory = Persistence.createEntityManagerFactory("db_task");}

    @PersistenceContext
    public static EntityManager entityManager;
    static {entityManager = entityManagerFactory.createEntityManager();}

    @Override
    public void create(Cars cars) {
        entityManager.persist(cars);
    }

    @Override
    public void update(Cars cars) {
        entityManager.merge(cars);
    }

    @Override
    public void delete(Cars cars) {
        entityManager.remove(cars);
    }

    @Override
    public Cars getCarById(int id) {
        return entityManager.find(Cars.class,id);
    }

    @Override
    public String getCarData(Cars cars) {
        return cars.getCarId()+" "+cars.getBrand()+" "+cars.getType()+" "+cars.getPlateNumber()+" "+cars.getYearOfManufacture()+" "+cars.getCalculatedValue()+" "+cars.getDrivenDistance()+" "+cars.isSent();
    }
}
