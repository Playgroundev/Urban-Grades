package Unit;

import com.dita.dev.Model.Trial;
import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.junit.AfterClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Iterator;
import java.util.List;

public class OrmTests {
  private static  SessionFactory factory;
    static{
        try{

        }catch (Throwable ex){
            throw new ExceptionInInitializerError(ex);
        }
    }



    @Test
    public void testPersitence(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dummy");
        EntityManager entityManager  = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Trial trial = new Trial();
        trial.setUsername("Njeri");

        entityManager.persist(trial);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }

    //@AfterClass
    @Test
    public  void testCommit(){
        Session session = factory.openSession();

        Transaction transaction = null;
        try{



        }catch(HibernateException ex ){
            ex.printStackTrace();
        }finally {
            session.close();
        }
    }
}
