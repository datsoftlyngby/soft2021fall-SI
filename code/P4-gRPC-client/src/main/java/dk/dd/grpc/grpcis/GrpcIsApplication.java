package dk.dd.grpc.grpcis;

import dk.dd.grpc.grpcis.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GrpcIsApplication
{
      
      public static void main(String[] args)
      {
            SpringApplication.run(GrpcIsApplication.class, args);
            List<String> myStudent = Client.getConnected("1003");
            System.out.println(myStudent);
      }
      
}
