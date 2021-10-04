package dk.dd.msaassignments.repo;

import dk.dd.msaassignments.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>
{
            List<Task> findByType(String type);
            List<Task> findAll();
            // Task  findById(long id);
}
