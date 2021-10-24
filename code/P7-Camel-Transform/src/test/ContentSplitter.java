package dk.dd.ox;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ContentSplitter extends RouteBuilder
{
    private static final String SOURCE  = "src/data/producer";
    private static final String DESTINATION1  = "src/data/consumer1";
    private static final String DESTINATION2 = "src/data/consumer2";

    @Override
    public void configure() throws Exception
    {
        from("stream:in?promptMessage=Enter message:")
           .choice()
                .when(bodyAs(String.class).contains("Camel"))
                    .log("About Camel")
                     .to("file:" + DESTINATION1)
                .otherwise()
                    .log("Not about Camel")
                .to("file:" + DESTINATION2)
           .end();
        /*
        from("timer:mytimer?period={{time}}")
                .setBody(simple("{{sender}} greets you"))
                .to("log:out");*/
    }

}
