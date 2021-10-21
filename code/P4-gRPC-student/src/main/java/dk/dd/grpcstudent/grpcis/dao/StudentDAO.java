package dk.dd.grpcstudent.grpcis.dao;

import dk.dd.grpcstudent.grpcis.domain.Student;
import org.springframework.data.jpa.provider.PersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.NoSuchElementException;

public class StudentDAO
{
      public Student findById(String id)
      {
            // We use entity managers to manage our entities
            // EntityManagerFactory emf = Persistence.createEntityManagerFactory("student");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("students");
            EntityManager em = emf.createEntityManager();
            
            // We can find a record in the database for a given id using the method find()
            Student student = em.find(Student.class, id);
            
            // If there is no record found with the provided student id, then we throw a NoSuchElement exception
            if(student == null) {throw new NoSuchElementException("NO ID " + id);}
            
            // If everything works fine, return the retrieved data
            return student;
      }
}
