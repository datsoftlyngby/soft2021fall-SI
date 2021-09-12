DROP TABLE IF EXISTS Student;
CREATE TABLE Student (
  id INT PRIMARY KEY,
  name VARCHAR(20) NOT NULL,
  mail VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS Exam;
CREATE TABLE Exam(
                         id INT PRIMARY KEY,
                         si VARCHAR(20) NOT NULL,
                         dls VARCHAR(20) NOT NULL,
                         tst VARCHAR(20) NOT NULL
);
