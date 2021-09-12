package dk.dd.grpc.grpcis.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import dk.dd.grpc.grpcis.service.ExamService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamServer
{
    // Let's use a logger to log everything that we want
    private static final Logger logger = Logger.getLogger(ExamServer.class.getName());

    // The good old main method is defined here :)
    public static void main(String[] args) {

        /*
            All you have to do here is to create an instance of the Server class,
            To create the instance we use the ServerBuilder,
            with the ServerBuilder we have to provide the port that our ResultService will listen to
            and then the handler which is the ResultServiceImpl class to handle the requests.
        */
        Server server = ServerBuilder.forPort(8082)
                .addService(new ExamService())
                .build();

        try {
            server.start();
            logger.log(Level.INFO, "EXAM SERVER STARTED ON PORT 8082");

            // This awaitTermination method will help to remain the server, otherwise the server will shutdown quickly
            server.awaitTermination();
        } catch (IOException e) {
            System.out.println(e);
            logger.log(Level.SEVERE, "EXAM SERVER DID NOT START");
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "SERVER SHUT DOWN ON INTERRUPTED");
        }

    }
}
