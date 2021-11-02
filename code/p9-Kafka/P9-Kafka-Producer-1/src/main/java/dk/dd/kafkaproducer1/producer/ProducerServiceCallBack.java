package dk.dd.kafkaproducer1.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

// This method will be called when the commit request sent to the server has been acknowledged
@Service
public class ProducerServiceCallBack
{
    private static final String TOPIC = "message-topic";
    private static Logger logger = LoggerFactory.getLogger(ProducerServiceCallBack.class);
    
    @Autowired
    // Ignore the compiler's warning
    private KafkaTemplate<String, String> template;
    
    public void sendMessageCallBack(String message)
    {
        ListenableFuture<SendResult<String, String>> future = template.send(TOPIC, message);
    
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>()
        {
            @Override
            public void onSuccess(SendResult<String, String> result)
            {
                logger.info("### Producer sends message [{}]", message);
                logger.info("$$$ Message [{}] delivered to partition [{}] with offset [{}]",
                        message, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            }
        
            @Override
            public void onFailure(Throwable ex)
            {
                logger.warn("$$$ Unable to deliver message [{}], with exception {}", message, ex.getMessage());
            }
        });
    }
}
