package Dao;

import DatabasePackage.HibernateOGMUtil;
import Model.Announcement;
import Model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AnnouncementDao {
    private EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    public List<Announcement> getAnnouncement() throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();


        String query = "FROM Announcement as h";
        List<Announcement> announcements = entityManager.createQuery( query , Announcement.class )
                .getResultList();
        System.out.println("the size is : " + announcements.size());
        if(announcements.size() == 0) return null;
        else return announcements;
    }
}
