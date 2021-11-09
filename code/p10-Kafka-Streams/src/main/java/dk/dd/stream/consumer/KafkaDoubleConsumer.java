package dk.dd.stream.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaDoubleConsumer
{
    @KafkaListener(topics = "${kafka.topic.doubled-output}")
    public void consume(Long number)
    {
        // System.out.println(String.format("Doubled :: %d", number));
    }

}
