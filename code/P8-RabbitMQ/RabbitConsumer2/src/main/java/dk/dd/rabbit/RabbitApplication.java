package dk.dd.rabbit;
/*
 * Fanout Consumer
 *
 * One of many, which will consume all messagescoming from a certain Exchange
 */
import com.rabbitmq.client.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class RabbitApplication {
    private final static String EXCHANGE_NAME = "fan-exchange";

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(RabbitApplication.class, args);
        connectQueue();
    }

    public static void connectQueue() throws Exception
    {
        // Same as the producer: tries to create a queue, if it wasn't already created
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Register for an exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // Get the automatically generated qname for this exchange
        String queueName = channel.queueDeclare().getQueue();
        // Bind the exchange to the queue
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [2] Waiting for messages. To exit press CTRL+C");

        // Get notified, if a message for this receiver arrives
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [2] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}

