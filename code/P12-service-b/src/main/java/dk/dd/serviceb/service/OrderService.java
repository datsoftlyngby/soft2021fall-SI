package dk.dd.serviceb.service;
import dk.dd.serviceb.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService
{
    List<Order> getOrders();
    void createOrder(Order order);
}
