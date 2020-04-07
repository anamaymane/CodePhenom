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
        if(entityManagerFactory == null) System.out.println("entity manager factory is null");

        EntityManager em = entityManagerFactory.createEntityManager();
        if(em == null) System.out.println("em is null");
        Announcement announcement = new Announcement("Tile number 1","the body", new Timestamp(new Date().getTime()));

        em.getTransaction().begin();

        em.persist( announcement );

        em.getTransaction().commit();

        em.close();

        HibernateOGMUtil.closeEntityManagerFactory();
    }


}
