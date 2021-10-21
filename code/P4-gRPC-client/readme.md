There are three applications and two databases. Their task is to provide aggregated information about students and their exams.

Student is an API and a gRPC server, operating the Student database. It is also a gRPC client to the Exam Application.
Exam application is an API and a gRPC server, operating Exam database.
Client application is a gRPC client to Student. Client invokes a service in Student, in which Student invokes a service in Exam.

After getting response from Exam, Student packs and sends back a response to Client.

This is a demo of gRPC integration.
