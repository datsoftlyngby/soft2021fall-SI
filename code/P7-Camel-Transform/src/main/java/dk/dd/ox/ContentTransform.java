package dk.dd.ox;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

public class ContentTransform extends RouteBuilder
{
    private static final String SOURCE  = "src/data/producer";
    private static final String DESTINATION1  = "src/data/consumer1";
    private static final String DESTINATION2 = "src/data/consumer2";

    @Override
    public void configure() throws Exception
    {
                from("file:" + SOURCE + "?delete=true")
                .process(new ContentProcessor())
                .to("file:" + DESTINATION1);
    }
}


