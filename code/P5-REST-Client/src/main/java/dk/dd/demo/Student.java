package dk.dd.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Entity object, which is used in ORM, as a RDB table
@Entity
// Lombok will take care to expand the class
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    // The ORM will take care to increment it
    @Id
    @GeneratedValue
    int id;
    String name;
    String mail;
}
