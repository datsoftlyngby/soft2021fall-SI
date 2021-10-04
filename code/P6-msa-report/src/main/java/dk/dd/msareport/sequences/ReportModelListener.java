package dk.dd.msareport.sequences;

import dk.dd.msareport.model.Report;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class ReportModelListener extends AbstractMongoEventListener<Report>
{
      private SequenceGeneratorService sequenceGenerator;
      
      @Autowired
      public ReportModelListener(SequenceGeneratorService sequenceGenerator)
      {
            this.sequenceGenerator = sequenceGenerator;
      }
      
      @Override
      public void onBeforeConvert(BeforeConvertEvent<Report> event)
      {
            if (event.getSource().getId() < 1)
            {
                  event.getSource().setId(sequenceGenerator.generateSequence(Report.SEQUENCE_NAME));
            }
      }
}
