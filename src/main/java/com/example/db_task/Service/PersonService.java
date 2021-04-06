package com.example.db_task.Service;

import com.example.db_task.Model.Cars;
import com.example.db_task.Model.PersonData;

import java.util.List;

public interface PersonService {

    void create(PersonData personData);

    void update(PersonData personData);

    void delete(PersonData personData);

    PersonData getPersonById(long id);

    List<Cars> getCarsByName(String name);

    Integer getLanguageIdByName(String name);

    PersonData getPersonByName(String name);
}
