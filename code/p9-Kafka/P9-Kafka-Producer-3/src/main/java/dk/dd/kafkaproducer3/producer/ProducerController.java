package dk.dd.kafkaproducer3.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import dk.dd.kafkaproducer3.model.Customer;

import java.awt.*;
import java.util.concurrent.CountDownLatch;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/kafka")
public class ProducerController
{
      @Value("${spring.kafka.producer.topic}")
      String topic;
      
      private static Logger logger = LoggerFactory.getLogger(ProducerService.class);
      
      @Autowired
      private ProducerService service;
      
      @PostMapping(value = "/customer")
      public String sendMyObject(@RequestBody Customer customer)
      {
            service.sendObject(customer);
            return "Customer published: " + customer.getId() + ":" + customer.getName() ;
      }
}
