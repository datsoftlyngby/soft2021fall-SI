package dk.dd.serviceb.controller;

import dk.dd.serviceb.entity.Order;
import dk.dd.serviceb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    private OrderService myOrder;

    @GetMapping("/all")
    public List<Order> getAllOrders()
    {
        return this.myOrder.getOrders();
    }

    @PostMapping("/")
    public void createOrder(@RequestBody Order purchaseOrder)
    {
        this.myOrder.createOrder(purchaseOrder);
    }

}
