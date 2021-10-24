package dk.dd.ox;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileSplitter extends RouteBuilder
{
    private static final String SOURCE  = "src/data/producer";
    private static final String DESTINATION1  = "src/data/consumer1";
    private static final String DESTINATION2 = "src/data/consumer2";

    @Override
    public void configure() throws Exception
    {
        from("file:" + SOURCE + "?delete=true")
           .choice()
              .when(simple("${file:ext} == 'txt'"))
                    .log("txt")
                    .to("file:" + DESTINATION1)
              .otherwise()
                    .log("csv")
                    .to("file:" + DESTINATION2)
                    .process(new ContentProcessor())
                    .to("file:"+ SOURCE)
       .end();
    }
}

