package dk.dd.grpcstudent.grpcis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

// Lombok will take care to expand the class
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
// Entity object, which is used in ORM, as a RDB table
// No need to create schema in advance, Hibernate creates it
@Entity
public class Student
{
      // The ORM will take care to increment the id
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private String id;
      private String name;
      private String mail;
}
