package com.example.db_task.Repositories;

import com.example.db_task.Model.CarsOfPeople;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarsOfPeopleCRUD extends CrudRepository<CarsOfPeople, Long> {

    @Query("SELECT car_id from CarsOfPeople where person_id = :pid")
    List<Integer> getCarsByUserId(@Param("pid") int person_id);
}
