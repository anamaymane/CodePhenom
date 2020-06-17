package Dao;

import DatabasePackage.HibernateOGMUtil;
import Model.Commentary;
import Model.Message;
import Model.Problem;
import Model.User;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "userDao")
@SessionScoped
public class UserDao {

    private EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    public void insertUser(String username, String email , String fullName, String password ) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user = new User(username, email,fullName,"Modify your description on UserArea",0,new Timestamp(new Date().getTime()),"user",password);

        entityManager.persist( user );

        entityManager.getTransaction().commit();

        HibernateOGMUtil.closeEntityManagerFactory(entityManagerFactory);
    }

    public void insertMessage(String receiver, String sender, String content, String object , Timestamp date) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Message message = new Message(receiver,sender,content,object,date);

        entityManager.persist( message );

        entityManager.getTransaction().commit();

        HibernateOGMUtil.closeEntityManagerFactory(entityManagerFactory);
    }

    public User getUser(String email, String password) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();


        String query = "FROM User as h where h.email = :email and h.password = :password";
        List<User> users = entityManager.createQuery( query , User.class ).setParameter("email",email)
                .setParameter("password",password)
                .getResultList();
        System.out.println("the size is : " + users.size());
        if(users.size() == 0) return null;
        else return users.iterator().next();
    }

    public void changeUserPassword(String username, String newPassword) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM User as h where username = :user";

        User user = entityManager.createQuery( query , User.class )
                .setParameter("user",username)
                .getSingleResult();

        user.setPassword(newPassword);

        entityManager.getTransaction().commit();

        HibernateOGMUtil.closeEntityManagerFactory(entityManagerFactory);

    }

    public void changeUserDescription(String username, String description) throws ClassNotFoundException {

        System.out.println("Entered in add desc of user : " + username);

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM User as h where username = :user";

        User user = entityManager.createQuery( query , User.class )
                .setParameter("user",username)
                .getSingleResult();

        user.setDescription(description);

        entityManager.getTransaction().commit();

        HibernateOGMUtil.closeEntityManagerFactory(entityManagerFactory);

    }

    public int getRegistredUsersSize() throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM User as h";

        List<User> users = entityManager.createQuery( query , User.class )
                .getResultList();

        System.out.println("the size is : " + users.size());

        return users.size();
    }

    public List<User> getUserOrderedByScore() throws ClassNotFoundException {
        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();


        String query = "FROM User as h order by score DESC";
        List<User> users = entityManager.createQuery( query , User.class )
                .setMaxResults(6)
                .getResultList();

        return users;
    }

    public List<Message> getUserMessagesReceived(String username) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM Message as h where receiver = :user or sender = :user order by date DESC";
        List<Message> messages = entityManager.createQuery( query , Message.class )
                .setParameter("user",username)
                .getResultList();

        return messages;
    }

    public User getUserByUsername(String username) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM User as h where username = :user";
        List<User> users = entityManager.createQuery( query , User.class )
                .setParameter("user",username)
                .getResultList();

        if(users.size() == 0) return null;
        else return users.iterator().next();
    }



    public int isUserAlreadyRegistred(String username, String email) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM User as h";
        List<User> users = entityManager.createQuery( query , User.class ).getResultList();

        for(User user : users){
            if(user.getEmail().equals(email) || user.getUsername().equals(username)){
                return 1;
            }
        }
        return 0;
    }
}
