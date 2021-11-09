package dk.dd.stream.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaSquareConsumer
{
    @KafkaListener(topics = "${kafka.topic.squared-output}")
    public void consume(Long number)
    {
        // System.out.println(String.format("Squared :: %d", number));
    }

}
