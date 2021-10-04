package dk.dd.msareport.sequences;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "all_sequences")
public class Sequences
{
      @Id
      private String id;
      private long seq;
}
