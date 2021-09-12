package dk.dd.soap.soapcl;

import dk.dd.soap.client.GeoIPServiceLocator;
import dk.dd.soap.client.GeoIPServiceSoap_PortType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoapclApplication
{
      
      public static void main(String[] args)
      {
            String country;
            String ip = args[0];
            try
            {
                  GeoIPServiceLocator locator = new GeoIPServiceLocator();
                  GeoIPServiceSoap_PortType service = locator.getGeoIPServiceSoap();
                  
                  country = service.getIpLocation(ip);
                  System.out.println(country);
            }
            catch (javax.xml.rpc.ServiceException ex)
            {
                  ex.printStackTrace();
            }
            catch (java.rmi.RemoteException ex)
            {
                  ex.printStackTrace();
            }
            
            // SpringApplication.run(SoapclApplication.class, args);
      }
}

