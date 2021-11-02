package dk.dd.kafkaproducer2.producer;

import dk.dd.kafkaproducer2.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProducerService
{
    private static final String TOPIC = "customer-topic";
    private static Logger logger = LoggerFactory.getLogger(ProducerService.class);
    
    @Autowired
    // Ignore the compiler's warning
    private KafkaTemplate<String, Object> template;
    
    public void sendObject(Customer customer)
    {
        template.send(TOPIC, new Customer(customer.getId(), customer.getName()));
        logger.info("### Producer sends customer [{}]", customer.getName());
        template.flush();
    }
}
