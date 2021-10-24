package dk.dd.ox;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OxApplication
{
    public static void main(String[] args)
    {
        CamelContext ctx = new DefaultCamelContext();
        // FileSplitter routeBuilder = new FileSplitter();
        // ContentSplitter routeBuilder = new ContentSplitter();
        ContentTransform routeBuilder = new ContentTransform();

        try
        {
            ctx.addRoutes(routeBuilder);
            ctx.start();
            Thread.sleep(5000);
            ctx.stop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        SpringApplication.run(OxApplication.class, args);
    }

}
