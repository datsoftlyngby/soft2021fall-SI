package dk.dd.grpc.grpcis.client;

import io.grpc.Channel;
import java.util.ArrayList;
import java.util.List;

import dk.dd.grpcis.stubs.exam.ExamRequest;
import dk.dd.grpcis.stubs.exam.ExamResponse;
import dk.dd.grpcis.stubs.exam.ExamServiceGrpc;
import io.grpc.Channel;

import java.util.ArrayList;
import java.util.List;

public class Client
{
            // Blocking stub means that the requests are blocked until the responses are come for previous requests
            // There are some other options as well, apart from Blocking stub, you can try those
            // But in this case let's go with this one
            private ExamServiceGrpc.ExamServiceBlockingStub examServiceBlockingStub;
            
            // To create the blocking stub, we need something called a channel
            // This channel is passed by the calling service.
            // So, when we call this method from the student service class, we have to provide the channel
            // Now, just add this code and we will see what is a channel in student service
            public Client(Channel channel)
            {
                  examServiceBlockingStub = ExamServiceGrpc.newBlockingStub(channel);
            }
            
            // This method will create a request for you and get the response back from the exam service and sent it to you
            public List<String> getResults(String id)
            {
                  // Creating the request object
                  ExamRequest examRequest = ExamRequest.newBuilder().setId(id).build();
                  // Getting the response back
                  ExamResponse examResponse = examServiceBlockingStub.getExamForStudent(examRequest);
                  
                  // Send it to the caller, in an appropriate manner in this case as a list.
                  List<String> exams = new ArrayList<>();
                  exams.add(examResponse.getSi().toString());
                  exams.add(examResponse.getDls().toString());
                  exams.add(examResponse.getTst().toString());
                  
                  return exams;
            }
      }
