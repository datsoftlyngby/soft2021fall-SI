package dk.dd.cameldemo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelSimple extends RouteBuilder
{
    @Override
    public void configure() throws Exception
    {
        from("stream:in?promptMessage=Enter message:")
           .choice()
                .when(bodyAs(String.class).contains("Camel"))
                    .to("file:src/main/resources/Camel")
                .otherwise()
                    .to("file:src/main/resources/Others")
                .end();
    }

}
