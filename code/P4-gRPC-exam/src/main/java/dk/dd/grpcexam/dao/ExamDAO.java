package dk.dd.grpcexam.dao;

import dk.dd.grpcexam.domain.Exam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.NoSuchElementException;

public class ExamDAO
{
      public static Exam findById(String id)
      {
            // We use entity managers to search in the database
            // The persistenceUnitName will  be reffered in the persistence.xml file in META-INF folder
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("exams");
            EntityManager em = emf.createEntityManager();
            
            // We search database for a record with the given  id using the predefined find() method
            Exam result = em.find(Exam.class, id);
            
            // If there is no record found with the provided student id, then we throw a NoSuchElement exception.
            if(result == null)
            {
                  throw new NoSuchElementException("NO DATA FOUND WITH THE ID "+ id);
            }
            
            // If record has been found,, return the result.
            return result;
      }
}
