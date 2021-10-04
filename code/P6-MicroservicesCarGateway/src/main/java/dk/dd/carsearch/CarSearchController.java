package dk.dd.carsearch;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class CarSearchController
{
    private CarSearchClient carClient = null;

    public CarSearchController(CarSearchClient carClient)
    {
        this.carClient = carClient;
    }
    
    @ResponseBody
    @CrossOrigin(origins = "*") // allow request from any client
    @HystrixCommand(fallbackMethod = "fallback") // in case of failure
    @GetMapping("/mycars")
    public Collection<Car> myCars()
    {
        return carClient.readCars()
                .getContent()
                .stream()
                .filter(this :: isMine)
                .collect(Collectors.toList());
    }

    private boolean isMine(Car car)
    {
        return  car.getBrand().equals("Mini") ||
                (car.getYear() > 2020 && car.getKm()< 50000);
    }

    private Collection<Car> fallback()
    {
        return new ArrayList<>();
    }
}
