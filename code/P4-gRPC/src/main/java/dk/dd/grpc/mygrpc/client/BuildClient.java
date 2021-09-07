package dk.dd.grpc.mygrpc.client;

import dk.dd.grpc.HelloRequest;
import dk.dd.grpc.HelloResponse;
import dk.dd.grpc.HelloServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

@Component
public class BuildClient
{
            public static void main(String[] args)
            {
                  ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                          .usePlaintext()
                          .build();
                  
                  HelloServiceGrpc.HelloServiceBlockingStub stub
                          = HelloServiceGrpc.newBlockingStub(channel);
                  
                  HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                          .setFirstName(args[0])
                          .setLastName(args[1])
                          .build());
      
                  System.out.println("Response received from server:\n" + helloResponse);
                  
                  channel.shutdown();
            }
      }
