package dk.dd.stream.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaNumberProducer
{
    private long counter = 0;

    @Autowired
    private KafkaTemplate<String, Long> template;

    @Scheduled(fixedRate = 20)
    public void produce()
    {
        System.out.println("Produced:  " + counter);
        this.template.sendDefault(counter++);
        // this.template.send(topic, message);
    }

}
