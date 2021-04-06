package com.example.db_task.Service;

import com.example.db_task.Model.Cars;
import com.example.db_task.Model.PersonData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    @Transactional
    @Override
    public void create(PersonData personData) {

    }

    @Transactional
    @Override
    public void update(PersonData personData) {

    }

    @Transactional
    @Override
    public void delete(PersonData personData) {

    }

    @Override
    public PersonData getPersonById(long id) {
        return null;
    }

    @Transactional
    @Override
    public List<Cars> getCarsByName(String name) {
        return null;
    }

    @Transactional
    @Override
    public Integer getLanguageIdByName(String name) {
        return null;
    }

    @Transactional
    @Override
    public PersonData getPersonByName(String name) {
        return null;
    }
}
