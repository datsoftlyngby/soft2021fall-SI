package dk.dd.serviceb.service;
import dk.dd.serviceb.entity.Order;
import dk.dd.serviceb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService
{

    @Autowired
    private OrderRepository por;

    @Override
    public List<Order> getOrders()
    {
        return this.por.findAll();
    }

    @Override
    public void createOrder(Order order)
    {
        this.por.save(order);
    }

}
