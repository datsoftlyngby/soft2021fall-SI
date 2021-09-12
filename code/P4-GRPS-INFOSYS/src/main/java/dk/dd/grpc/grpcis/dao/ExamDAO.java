package dk.dd.grpc.grpcis.dao;

import dk.dd.grpc.grpcis.domain.Exam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.NoSuchElementException;

public class ExamDAO
{
      public static Exam findById(String id)
      {
            // We use entity managers to manage our entities
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("students-infosys");
            EntityManager em = emf.createEntityManager();
            
            // We can find a record in the database for a given id using the find method.
            Exam result = em.find(Exam.class, id);
            
            // If there is no record found with the provided student id, then we throw a NoSuchElement exception.
            if(result == null){
                  throw new NoSuchElementException("NO DATA FOUND WITH THE ID "+ id);
            }
            
            // If everything worked fine, return the result.
            return result;
      }
}
