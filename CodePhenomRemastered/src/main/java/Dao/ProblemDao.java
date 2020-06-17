package Dao;

import DatabasePackage.HibernateOGMUtil;
import Model.Commentary;
import Model.Problem;
import Model.User;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Timestamp;
import java.util.*;

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

    public Problem getProblemById(Long id) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM Problem as h where problemId = :id";
        Problem problem = entityManager.createQuery( query , Problem.class )
                .setParameter("id",id)
                .getSingleResult();

        Collections.sort(problem.getCommentaries(), new CommentariesSort());

        return problem;
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

    public void insertCommentIntoProblem(Long problemId,String username,String content) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM Problem as h where problemId = :problemId";

        Problem problem = entityManager.createQuery( query , Problem.class )
                .setParameter("problemId",problemId)
                .getSingleResult();

        Commentary comments = new Commentary(username, new Timestamp(System.currentTimeMillis()), content);

        problem.getCommentaries().add(comments);

        entityManager.getTransaction().commit();

        HibernateOGMUtil.closeEntityManagerFactory(entityManagerFactory);
    }
}

class CommentariesSort implements Comparator<Commentary> {



    public int compare(Commentary a, Commentary b) {
        if (a.getDate().getTime() < b.getDate().getTime()) {
            return 1;
        } else {
            return -1;
        }
    }
}
