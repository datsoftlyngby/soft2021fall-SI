package dk.dd.grpcstudent;

import dk.dd.grpcstudent.grpcis.server.StudentServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GRpcStudentApplication
{
      public static void main(String[] args)
      {
            SpringApplication.run(GRpcStudentApplication.class, args);
            // StudentServer.runServer();
      }

      
}
