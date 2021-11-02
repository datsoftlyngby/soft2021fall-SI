package dk.dd.kafkaproducer3.producer;
// Kafka producer sends a number of JSON messages to a broker with 3 partitions
// A group of 3 consumers consumes the messages in a different format each: JSON, text, and binary
import dk.dd.kafkaproducer3.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService
{
    @Value("${spring.kafka.producer.topic}")
    String topic;
    private static Logger logger = LoggerFactory.getLogger(ProducerService.class);
    
    @Autowired
    // Ignore the compiler's warning
    private KafkaTemplate<String, Object> template;
    
    public void sendObject(Customer customer)
    {
        template.send(topic, new Customer(customer.getId(), customer.getName()));
        logger.info("### Producer sends customer [{}:{}]", customer.getId(), customer.getName());
        template.flush();
    }
}

