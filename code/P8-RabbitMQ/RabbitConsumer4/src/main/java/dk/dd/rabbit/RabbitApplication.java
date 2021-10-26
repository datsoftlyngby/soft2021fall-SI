package dk.dd.rabbit;
/*
 * Topic Binding
 * binding key = "<size>.<character>.<species>"
 * We will run this application three times in parallel, with different binding keys
 * We observe which binding key matches to the advertised message topic (the message routingKey)
 *
 * B1 = "little.mean.*"
 * B2 = "*.*.person"
 * B3 = "#.kitty"
 */
import com.rabbitmq.client.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class RabbitApplication
{
    private static final String EXCHANGE_NAME = "topic-exchange";

    private static String B1 = "little.mean.*";
    private static String B2 = "*.*.person";
    private static String B3 = "#.kitty";
    private static String bindingKey = B2;


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
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // Get the automatically generated qname for this exchange
        String queueName = channel.queueDeclare().getQueue();
        // Bind the exchange to the queue
        channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
        //channel.queueBind(queueName, EXCHANGE_NAME, "");

        //System.out.println( "[1] Waiting for messages with pattern " + B1 + " To exit press CTRL+C");
        System.out.println( "[2] Waiting for messages with pattern " + B2 + " To exit press CTRL+C");
        // System.out.println( " [4] Waiting for messages with pattern " + B3 + " To exit press CTRL+C");

        // Get notified, if a message for this receiver arrives
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [4] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" +  message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}

