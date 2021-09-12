package dk.dd.grpc.grpcis.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import dk.dd.grpc.grpcis.service.StudentService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentServer
{
    // To log everything the server operations
    private static final Logger logger = Logger.getLogger(StudentServer.class.getName());
    
    public static void main(String[] args)
    {
        Server server = ServerBuilder.forPort(8081)
                .addService(new StudentService())
                .build();

        try
        {
            server.start();
            logger.log(Level.INFO, "STUDENT SERVER STARTED ON PORT 8081");
            server.awaitTermination();
        }
        catch (IOException e)
        {
            logger.log(Level.SEVERE, "STUDENT SERVER DID NOT START");
        }
        catch (InterruptedException e)
        {
            logger.log(Level.SEVERE, "SERVER SHUT DOWN ON INTERRUPTED");
        }
    }
}
