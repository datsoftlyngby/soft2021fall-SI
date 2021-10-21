package dk.dd.grpcstudent.grpcis.gRPCexamClient;

import dk.dd.grpcexam.stubs.exam.ExamRequest;
import dk.dd.grpcexam.stubs.exam.ExamResponse;
import dk.dd.grpcexam.stubs.exam.ExamServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.ArrayList;
import java.util.List;

public class ExamClient
{
            // Create a request to the Exam server, send it and get response from the predefined service there
            public static List<String> getConnected(String id)
            {
                  // Client creates a channel and a stub for connecting the Server
                  ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:9091")
                          .usePlaintext()
                          .build();

                  // Blocking stub means that the requests are blocked until the responses are come for previous requests
                  ExamServiceGrpc.ExamServiceBlockingStub examServiceBlockingStub = ExamServiceGrpc.newBlockingStub(channel);
                  
                  // Creating the request object
                  ExamRequest examRequest = ExamRequest.newBuilder().setId(id).build();
                  // Getting the response
                  ExamResponse examResponse = examServiceBlockingStub.getExamForStudent(examRequest);
      
                  // Process the response appropriately and send it back to the caller - in this case, the StudentService
                  List<String> exams = new ArrayList<>();
                  exams.add(examResponse.getSi().toString());
                  exams.add(examResponse.getDls().toString());
                  exams.add(examResponse.getTst().toString());
                  return exams;

            }
      }
