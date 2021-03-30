package com.example.db_task.Repositories;

import com.example.db_task.TableClasses.Cars;
import com.example.db_task.TableClasses.PersonData;

import java.util.List;

public interface PersonRepository {

    PersonData getPersonById(long id);
    List<Cars> getCarsByPerson(String name);
}
