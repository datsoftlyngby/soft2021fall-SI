package dk.dd.rest.level1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


// Lombok will take care to expand the class
@Data
@AllArgsConstructor
@NoArgsConstructor
// Entity object, which is used in ORM, as a RDB table
// No need to create schema in advance, Hibernate creates it
@Entity
public class Student
{
    // The ORM will take care to increment the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String mail;
}
