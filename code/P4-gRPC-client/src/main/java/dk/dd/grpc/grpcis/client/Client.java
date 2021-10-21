package dk.dd.grpc.grpcis.client;

import java.util.ArrayList;
import java.util.List;
import dk.dd.grpcstudent.stubs.student.StudentRequest;
import dk.dd.grpcstudent.stubs.student.StudentResponse;
import dk.dd.grpcstudent.stubs.student.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.logging.Logger;

public class Client
{
            private static final Logger logger = Logger.getLogger(Client.class.getName());
            
            // Create a request to the Student server, send it and get response from the predefined service there
            public static List<String> getConnected(String id)
            {
                  // Client creates a channel and a stub for connecting the Server
                  ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:9090")
                          .usePlaintext()
                          .build();
                  
                  // Blocking stub means that the requests are blocked until the responses are come for previous requests
                  StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc.newBlockingStub(channel);
                  
                  // Creating the request object
                  StudentRequest studentRequest = StudentRequest.newBuilder().setId(id).build();
                  // Getting the response
                  StudentResponse studentResponse = stub.getStudentInfo(studentRequest);
                  
                  // Process the response appropriately and send it back to the caller - in this case, the StudentService
                  List<String> mystudent = new ArrayList<>();
                  mystudent.add(studentResponse.getName());
                  mystudent.add(studentResponse.getMail());
                  mystudent.add(studentResponse.getSi().toString());
                  mystudent.add(studentResponse.getDls().toString());
                  mystudent.add(studentResponse.getTst().toString());
                  return mystudent;
            }
}

