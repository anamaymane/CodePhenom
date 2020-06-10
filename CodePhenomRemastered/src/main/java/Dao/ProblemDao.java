package Dao;

import DatabasePackage.HibernateOGMUtil;
import Model.Problem;
import Model.User;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@ManagedBean(name = "problemDao")
@ApplicationScoped
public class ProblemDao {

    private EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    public int getProblemSize() throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM Problem as h";

        List<Problem> problems = entityManager.createQuery( query , Problem.class )
                .getResultList();

        System.out.println("the size is : " + problems.size());

        return problems.size();
    }
}
