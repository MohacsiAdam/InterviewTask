package com.example.db_task.Repositories;

import com.example.db_task.Model.PersonData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonCRUD extends CrudRepository<PersonData, Long> {

    @Query("SELECT p FROM PersonData p WHERE p.name = :name")
    PersonData getPersonDataByName(@Param("name") String name);
}
