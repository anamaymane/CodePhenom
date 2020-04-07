package queryTesting;

import DatabasePackage.HibernateOGMUtil;
import Model.Announcement;
import Model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;

import java.sql.Timestamp;
import java.util.Date;

public class UserInsert {

    private static EntityManagerFactory entityManagerFactory;


    public static void setUpEntityManagerFactoryAndPopulateTheDatastore() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        HibernateOGMUtil.setUpEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Announcement announcement = new Announcement("Tile number 1","the body", new Timestamp(new Date().getTime()));

        entityManager.persist( announcement );

        entityManager.getTransaction().commit();

        HibernateOGMUtil.closeEntityManagerFactory();
    }


}
