package dk.dd.graphqldemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "firstname", nullable = false)
    private String firstname;
    
    @Column(name = "lastname", nullable = false)
    private String lastname;
    
    @Column(name = "email")
    private String email;

}
