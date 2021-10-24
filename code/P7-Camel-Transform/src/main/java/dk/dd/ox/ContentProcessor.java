package dk.dd.ox;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class ContentProcessor implements Processor
{
    public static Calendar cal = Calendar.getInstance();

    public void process(Exchange exchange) throws Exception
    {
        String custom = exchange.getIn().getBody(String.class);
        System.out.println(custom);
        StringBuilder csv = new StringBuilder();
        csv.append(custom.trim());
        csv.append(",").append(cal.DATE);
        csv.append(",").append("done");
        exchange.getIn().setBody(csv.toString());
    }
}


