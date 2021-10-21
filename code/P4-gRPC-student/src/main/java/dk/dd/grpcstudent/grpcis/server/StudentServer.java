package dk.dd.grpcstudent.grpcis.server;

import dk.dd.grpcstudent.grpcis.service.StudentService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentServer
{
    private static final Logger logger = Logger.getLogger(StudentServer.class.getName());
    
    public static void main(String[] args)
    // public static void runServer()
    {
        // Builder creates a gRPC server on the specified port and adds the StudentService service that we defined to it
        Server server = ServerBuilder.forPort(9090)
                .addService(new StudentService())
                .build();
        try
        {
            server.start();
            logger.log(Level.INFO, "STUDENT SERVER STARTED ON PORT 9090");
            // Keep the server running in the foreground blocking the prompt
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
