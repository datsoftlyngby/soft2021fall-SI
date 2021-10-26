package dk.dd.rabbit;
/*
 * Routing Producer
 *
 * Sends a message and a key (routingKey)
 * Only subscribed consumers will receive it - must be bound to the same Exchange
 *
 * The message text comes as an arguments, while the key is hard coded: spam
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitApplication
{
    //private final static String QUEUE_NAME = "helloqueue";
    // non-durable, exclusive, auto-delete queue with an automatically generated name
    private static String queueName = null; // never used here
    private final static String EXCHANGE_NAME = "direct-exchange";
    private static String routingKey = "spam";

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(RabbitApplication.class, args);
        String message = args.length < 1 ? "Hello World!" : String.join(" ", args);
        createQueue(message);
        System.out.println(" [3] Sent routing key '" + routingKey + "' for message '" + message + "'");
    }

    public static void createQueue(String message) throws Exception
    {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel())
            {
                // channel.queueDeclare(queueName, false, false, false, null);
                channel.exchangeDeclare(EXCHANGE_NAME, "direct");
                channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            }

     }
}