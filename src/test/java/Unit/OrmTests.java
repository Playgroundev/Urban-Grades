package Unit;

import com.dita.dev.Model.Trial;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OrmTests {

    @Test
    public void testPersitence(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dummy");
        EntityManager entityManager  = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Trial trial = new Trial();
        trial.setUsername("Kamau");

        entityManager.persist(trial);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();

    }

}
