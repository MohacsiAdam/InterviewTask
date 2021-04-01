package com.example.db_task.Repositories;

import com.example.db_task.TableClasses.CarsOfPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class CarsOfPeopleRepositoryImpl implements CarsOfPeopleRepository {

    @Autowired
    private EntityManager entityManager;

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
