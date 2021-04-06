package com.example.db_task.Service;

import com.example.db_task.Model.Cars;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarServiceImpl implements CarService{

    @Transactional
    @Override
    public void create(Cars cars) {

    }

    @Transactional
    @Override
    public void update(Cars cars) {

    }

    @Transactional
    @Override
    public void delete(Cars cars) {

    }

    @Transactional
    @Override
    public Cars getCarById(int id) {
        return null;
    }

    @Transactional
    @Override
    public String getCarData(Cars cars) {
        return null;
    }
}
