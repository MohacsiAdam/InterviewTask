package com.example.db_task.Repositories;

import com.example.db_task.TableClasses.CarsOfPeople;

import javax.persistence.*;
import java.util.List;

public class CarsOfPeopleRepositoryImpl implements CarsOfPeopleRepository {

    @PersistenceContext
    public static EntityManagerFactory entityManagerFactory;
    static {entityManagerFactory = Persistence.createEntityManagerFactory("db_task");}

    @PersistenceContext
    public static EntityManager entityManager;
    static {entityManager = entityManagerFactory.createEntityManager();}

    @Override
    public void create(CarsOfPeople carsOfPeople) {
        entityManager.persist(carsOfPeople);
    }

    @Override
    public void update(CarsOfPeople carsOfPeople) {
        entityManager.merge(carsOfPeople);
    }

    @Override
    public void delete(CarsOfPeople carsOfPeople) {
        entityManager.remove(carsOfPeople);
    }

    @Override
    public List<Integer> getCarIdsFromUserId(long id) {
        TypedQuery<Integer> idQuery = entityManager.createQuery("SELECT car_id FROM CarsOfPeople WHERE person_id = " + id,Integer.class);
        return idQuery.getResultList();
    }

    @Override
    public List<Integer> getPersonIdFromCarId(long id) {
        TypedQuery<Integer> idQuery = entityManager.createQuery("SELECT person_id FROM CarsOfPeople WHERE car_id = " + id,Integer.class);
        return idQuery.getResultList();
    }
}
