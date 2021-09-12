package dk.dd.grpc.grpcis.service;

import dk.dd.grpcis.stubs.exam.Grade;
import dk.dd.grpcis.stubs.exam.ExamRequest;
import dk.dd.grpcis.stubs.exam.ExamResponse;
import dk.dd.grpcis.stubs.exam.ExamServiceGrpc;
import dk.dd.grpc.grpcis.dao.ExamDAO;
import dk.dd.grpc.grpcis.domain.Exam;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamService<StreamObserver> extends ExamServiceGrpc.ExamServiceImplBase
{
      // We need to have an instance of the dao class to work with the database
      private ExamDAO examDao = new ExamDAO();
      
      private static final Logger logger = Logger.getLogger(ExamService.class.getName());
      
      // We have to override the getExamForStudent that was defined in the examService class
      // The examService class is an autogenerated class by the proto file
      // So, let's override the getExamForStudent method here.
      @Override
      public void getExamForStudent(ExamRequest request, io.grpc.stub.StreamObserver<ExamResponse> responseObserver)
      {
            String id = request.getId(); // the student ID should be passed with the request message
            
            try {
                  Exam exam = ExamDAO.findById(id); // Use the dao class to retrieve data
            
            /*
                In gRPC everything we create according to the builder pattern,
                here we have to generate the response message,
                in order to create that response message we use the response builder
                and then set the values for that, 
            */
                  ExamResponse examResponse = ExamResponse.newBuilder()
                          .setId(id)
                          .setSi(Grade.valueOf(exam.getSi()))
                          .setDls(Grade.valueOf(exam.getDls()))
                          .setTst(Grade.valueOf(exam.getTst()))
                          .build();
                  
                  responseObserver.onNext(examResponse);
                  responseObserver.onCompleted();
                  
            }
            catch (NoSuchElementException e)
            {
                  logger.log(Level.SEVERE, "NO exam FOUND WITH THE STUDENT ID :- " + id);
                  
                  // If some error occurs we sent an error with the following status which is not_found
                  responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
                  
            }
            
            
      }
}