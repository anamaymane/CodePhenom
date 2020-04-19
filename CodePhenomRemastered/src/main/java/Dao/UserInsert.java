package Dao;

import DatabasePackage.HibernateOGMUtil;
import Model.Announcement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.*;

import java.sql.Timestamp;
import java.util.Date;

public class UserInsert {

    EntityManager entityManager;

    private static EntityManagerFactory entityManagerFactory;


    public  void setUpEntityManagerFactoryAndPopulateTheDatastore() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException, ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Announcement announcement = new Announcement("Tile number 1","the body", new Timestamp(new Date().getTime()));

        entityManager.persist( announcement );

        entityManager.getTransaction().commit();

        HibernateOGMUtil.closeEntityManagerFactory(entityManagerFactory);
    }


}
