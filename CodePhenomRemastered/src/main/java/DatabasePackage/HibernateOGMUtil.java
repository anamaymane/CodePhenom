package DatabasePackage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateOGMUtil {
    private static EntityManagerFactory entityManagerFactory;

    @BeforeClass
    public static void setUpEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory( "test" );
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}
