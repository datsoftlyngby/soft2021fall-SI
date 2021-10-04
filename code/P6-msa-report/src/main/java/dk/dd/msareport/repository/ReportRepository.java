package dk.dd.msareport.repository;

import dk.dd.msareport.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends MongoRepository<Report, Long>
{
            //List<Report> findByType(String type);
            List<Report> findAll();
            Report  findById(long id);
}
