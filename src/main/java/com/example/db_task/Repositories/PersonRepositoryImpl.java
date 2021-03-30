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
    public PersonData getPersonById(long id) {
        return entityManager.find(PersonData.class,id);
    }

    @Override
    public List<Cars> getCarsByPerson(String name) {

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
