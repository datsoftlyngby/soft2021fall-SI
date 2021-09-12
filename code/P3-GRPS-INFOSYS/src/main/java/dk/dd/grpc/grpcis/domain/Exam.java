package dk.dd.grpc.grpcis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Exam
{
      @Id
      private String id;
      private String si;
      private String dls;
      private String tst;
}

