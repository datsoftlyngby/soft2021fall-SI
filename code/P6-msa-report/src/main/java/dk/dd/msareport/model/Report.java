package dk.dd.msareport.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="reports")
public class Report
{
      // Not to store this const in the Mongo database 'reports'
      @Transient
      public static final String SEQUENCE_NAME = "report-id-sequence";
      
      @Id
      private long id;
      private Long sid;
      private Long tid;
      private String comment;
}


