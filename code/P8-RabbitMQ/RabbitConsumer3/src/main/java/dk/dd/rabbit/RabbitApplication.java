package dk.dd.rabbit;
/*
 * Routing Consumer
 *
 * Receives only messages with a specified key
 *
 * We will run it two times in parallel: with a bindingKey as a subscription, and without a key
 */
import com.rabbitmq.client.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class RabbitApplication {
    private static final String EXCHANGE_NAME = "direct-exchange";
    private static String bindingKey = "no-spam";

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
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        // Get the automatically generated qname for this exchange
        String queueName = channel.queueDeclare().getQueue();
        // Bind the exchange to the queue
        channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
        //channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [3] Waiting for messages. To exit press CTRL+C");

        // Get notified, if a message for this receiver arrives
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [3] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" +  message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}

