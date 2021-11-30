package dk.dd.servicea.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User
{
      @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE)
      long id;
      String firstname;
      String lastname;
      String email;
}
