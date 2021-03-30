package com.example.db_task.Repositories;

import com.example.db_task.TableClasses.Cars;

public interface CarRepository {

    Cars getCarById(long id);
    String getCarData(Cars cars);


}
