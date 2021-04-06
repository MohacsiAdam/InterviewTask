package com.example.db_task.Repositories;

import com.example.db_task.Model.Cars;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarCRUD extends CrudRepository<Cars, Long> {


    @Query("select c from Cars c where c.carId in :cid")
    List<Cars> getCarsByOwner(@Param("cid") List<Integer> car_ids);
}
