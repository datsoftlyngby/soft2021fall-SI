package dk.dd.grpc.grpcis.dao;

import dk.dd.grpc.grpcis.domain.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.NoSuchElementException;

public class StudentDAO
{
      public Student findById(String id)
      {
            // We use entity managers to manage our entities
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("students-infosys");
            EntityManager em = emf.createEntityManager();
            
            // We can find a record in the database for a given id using the method find()
            Student student = em.find(Student.class, id);
            
            // If there is no record found with the provided student id, then we throw a NoSuchElement exception
            if(student == null) {throw new NoSuchElementException("NO ID " + id);}
            
            // If everything works fine, return the retrieved data
            return student;
      }
}
