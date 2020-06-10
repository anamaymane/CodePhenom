package Dao;

import DatabasePackage.HibernateOGMUtil;
import Model.Problem;
import Model.User;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

@ManagedBean(name = "problemDao")
@ApplicationScoped
public class ProblemDao {

    private EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    public List<Problem> getProblems(String displayWhat, String min, String max, String type) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Problem> problems = new ArrayList<Problem>();

        if(displayWhat.equals("all")) {

            String query = "FROM Problem as h";
            problems = entityManager.createQuery( query , Problem.class )
                    .getResultList();
        }
        else if(displayWhat.equals("difficulty")){

            int minF = Integer.valueOf(min);
            int maxF = Integer.valueOf(max);
            System.out.println("min : " + min + " max : " + max);
            String query = "FROM Problem as h where difficulty BETWEEN :min and :max";
            problems = entityManager.createQuery( query , Problem.class )
                    .setParameter("min",minF)
                    .setParameter("max",maxF)
                    .getResultList();
            System.out.println("The size is : " + problems.size());

        } else {

            String query = "FROM Problem as h where type = :typeInput";
            problems = entityManager.createQuery( query , Problem.class )
                    .setParameter("typeInput",type)
                    .getResultList();
        }
        return problems;
    }

    public List<String> getProblemsCategories() throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "select distinct h.type FROM Problem as h ";
        List<String> problems = entityManager.createQuery( query , String.class )
                .getResultList();
        return problems;
    }

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
