package dk.dd.rest.level2.repository;
import dk.dd.rest.level2.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This is the DAO layer - interface with the available methods
// Spring Data will generate them automatically
// The inherited interface has already declared basic CRUD methods, as findOne(), delete(), save(), ...
// see https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
@Repository
public interface StudentService extends JpaRepository<Student, Integer>
{
    // Here are additional customer defined search methods
    Student findByName(String name);
}

