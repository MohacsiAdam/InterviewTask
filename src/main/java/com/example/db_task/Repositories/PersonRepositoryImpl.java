package com.example.db_task.Repositories;

import com.example.db_task.TableClasses.Cars;
import com.example.db_task.TableClasses.PersonData;

import javax.persistence.*;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository{

    @PersistenceContext
    public static EntityManagerFactory entityManagerFactory;
    static {entityManagerFactory = Persistence.createEntityManagerFactory("db_task");}

    @PersistenceContext
    public static EntityManager entityManager;
    static {entityManager = entityManagerFactory.createEntityManager();}


    @Override
    public void create(PersonData personData) {
        entityManager.persist(personData);
    }

    @Override
    public void update(PersonData personData) {
        entityManager.merge(personData);
    }

    @Override
    public void delete(PersonData personData) {
        entityManager.refresh(personData);
    }

    @Override
    public PersonData getPersonById(long id) {
        return entityManager.find(PersonData.class,id);
    }

    @Override
    public Integer getLanguageIdByName(String name) {
        TypedQuery<Integer> returnId = entityManager.createQuery("SELECT languageId from PersonData WHERE name = '"+name+"'", Integer.class);
        return returnId.getSingleResult();
    }

    @Override
    public PersonData getPersonByName(String name) {
        return (PersonData) entityManager.createQuery("select p FROM PersonData p where name= '"+name+"'").getSingleResult();
    }

    @Override
    public List<Cars> getCarsByName(String name) {



        //Get Person ID from from name
        Object personIdQuery = entityManager
                .createQuery("SELECT personId FROM PersonData WHERE name= '"+name+"'")
                .getSingleResult();

        //Get Car IDs belonging to Person ID
        TypedQuery<Integer> idQuery = entityManager.createQuery("SELECT car_id FROM CarsOfPeople WHERE person_id = " + personIdQuery,Integer.class);
        List<Integer> carIds = idQuery.getResultList();

        //Get Car Parameters By Car IDs
        TypedQuery<Cars> carsTypedQuery = (entityManager.createQuery("SELECT c FROM Cars c WHERE c.carId IN (:ids)", Cars.class)
                .setParameter("ids", carIds));
        return carsTypedQuery.getResultList();
    }


}
