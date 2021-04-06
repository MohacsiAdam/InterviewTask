package com.example.db_task.Service;

import com.example.db_task.Model.Cars;

public interface CarService {

    void create(Cars cars);

    void update(Cars cars);

    void delete(Cars cars);

    Cars getCarById(int id);

    String getCarData(Cars cars);
}
