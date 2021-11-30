package dk.dd.serviceb.repository;

import dk.dd.serviceb.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>
{

    @Query("{ 'user.id': ?0 }")
    List<Order> findByUserId(long userId);

}
