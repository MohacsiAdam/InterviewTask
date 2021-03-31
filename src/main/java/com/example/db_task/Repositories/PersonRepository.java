package com.example.db_task.Repositories;

import com.example.db_task.TableClasses.Cars;
import com.example.db_task.TableClasses.PersonData;

import java.util.List;

public interface PersonRepository {

    void create(PersonData personData);

    void update(PersonData personData);

    void delete(PersonData personData);

    PersonData getPersonById(long id);

    List<Cars> getCarsByName(String name);

    Integer getLanguageIdByName(String name);

    PersonData getPersonByName(String name);
}
