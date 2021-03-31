package com.example.db_task.Repositories;

import com.example.db_task.TableClasses.CarsOfPeople;

import java.util.List;

public interface CarsOfPeopleRepository {

    void create(CarsOfPeople carsOfPeople);

    void update(CarsOfPeople carsOfPeople);

    void delete(CarsOfPeople carsOfPeople);

    List<Integer> getCarIdsFromUserId(long id);

    List<Integer> getPersonIdFromCarId(long id);
}
