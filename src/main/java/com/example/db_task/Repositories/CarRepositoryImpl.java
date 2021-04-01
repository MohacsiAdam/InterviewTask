package com.example.db_task.Repositories;

import com.example.db_task.TableClasses.Cars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

@Repository
public class CarRepositoryImpl implements CarRepository{

    @Autowired
    private EntityManager entityManager;


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
