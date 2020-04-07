package queryTesting;

import DatabasePackage.HibernateOGMUtil;
import Model.Announcement;
import Model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import java.sql.Timestamp;
import java.util.Date;

public class UserInsert {

    private static EntityManagerFactory entityManagerFactory;


    public static void setUpEntityManagerFactoryAndPopulateTheDatastore() throws SystemException, NotSupportedException {

        entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

        EntityManager em = entityManagerFactory.createEntityManager();

        Announcement announcement = new Announcement("Tile number 1","the body", new Timestamp(new Date().getTime()));

        em.getTransaction().begin();

        em.persist( announcement );

        em.getTransaction().commit();

        em.close();

        HibernateOGMUtil.closeEntityManagerFactory();
    }


}
