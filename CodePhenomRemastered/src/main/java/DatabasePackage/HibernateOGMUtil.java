package DatabasePackage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateOGMUtil {
    private static EntityManagerFactory entityManagerFactory = null;


    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("test");
        } catch (Exception e) {
            System.err.println("Initial EntityManagerFactory creation failed." + e);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}
