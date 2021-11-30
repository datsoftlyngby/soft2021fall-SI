package dk.dd.serviceb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.dd.serviceb.entity.Order;
import dk.dd.serviceb.entity.User;
import dk.dd.serviceb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceEventHandlerImpl implements UserServiceEventHandler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "user-event", groupId = "my-group")
    public void consume(String message)
    {
        try
        {
            User user = OBJECT_MAPPER.readValue(message, User.class);
            this.updateUser(user);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void updateUser(User user)
    {
        List<Order> userOrders = this.orderRepository.findByUserId(user.getId());
        userOrders.forEach(p -> p.setUser(user));
        this.orderRepository.saveAll(userOrders);
    }
}
