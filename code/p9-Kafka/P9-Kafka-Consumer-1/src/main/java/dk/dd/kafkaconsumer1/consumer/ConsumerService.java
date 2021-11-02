package dk.dd.kafkaconsumer1.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerService
{
      // get logger for my class
      private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
      
      @KafkaListener(topics = "message-topic", groupId = "my-group")
      public void consume(String message) throws IOException
      {
            System.out.println("Consumed message:" + message);
            logger.info("&&& Message [{}] consumed", message);
      }
}
      
