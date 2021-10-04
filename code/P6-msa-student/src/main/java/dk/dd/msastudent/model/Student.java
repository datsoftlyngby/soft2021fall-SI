package dk.dd.msastudent.model;

// extends class ResourceSupport, which provides method add() for links to other resources
// add HATEOAS dependency in maven for it

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Student
{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String mail;

    public Student()
    {
        super();
    }
}
