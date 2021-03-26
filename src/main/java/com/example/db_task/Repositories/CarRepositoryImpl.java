package com.example.db_task.Repositories;

import com.example.db_task.TableClasses.Cars;

import javax.persistence.EntityManager;

public class CarRepositoryImpl implements CarRepository{

    private EntityManager em;

    @Override
    public Cars getCarById(long id) {
        return em.find(Cars.class, id);
    }

    @Override
    public Cars saveCar(Cars cars) {
            em.persist(cars);


        return cars;
    }

    @Override
    public void deleteCar() {

    }
}
