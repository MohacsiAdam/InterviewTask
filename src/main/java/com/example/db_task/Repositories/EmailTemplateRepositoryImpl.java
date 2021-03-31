package com.example.db_task.Repositories;

import com.example.db_task.TableClasses.EmailTemplates;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class EmailTemplateRepositoryImpl implements EmailTemplatesRepository{

    @PersistenceContext
    public static EntityManagerFactory entityManagerFactory;
    static {entityManagerFactory = Persistence.createEntityManagerFactory("db_task");}

    @PersistenceContext
    public static EntityManager entityManager;
    static {entityManager = entityManagerFactory.createEntityManager();}

    @Override
    public void create(EmailTemplates emailTemplates) {
        entityManager.persist(emailTemplates);
    }

    @Override
    public void update(EmailTemplates emailTemplates) {
        entityManager.merge(emailTemplates);
    }

    @Override
    public void delete(EmailTemplates emailTemplates) {
        entityManager.remove(emailTemplates);
    }

    @Override
    public String getTextById(long language_id) {
        return String.valueOf(entityManager.createQuery("SELECT text from EmailTemplates WHERE language_id = "+language_id).getSingleResult());
    }
}
