package dk.dd.demo;

import com.sun.deploy.net.HttpResponse;
import org.hibernate.dialect.identity.JDataStoreIdentityColumnSupport;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.plaf.basic.BasicTextUI;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DemoOps
{
    private static BasicTextUI HttpClientBuilder;
    static final String endPointGetAll = "http://localhost:8060/students/all";
    static final String endPointGetOne = "http://localhost:8060/students/getByName/{id}}";
    static final String endPointSave = "http://localhost:8060/students/save";
    static final String endPointUpdateId = "http://localhost:8060/students/update/{id}";
    static final String endPointDelete = "http://localhost:8060/students/del(id}";


    public static void main(String[] args)
    {
        // RestTemplate is a class, which provides template for making HTTP requests to RESTful services
        RestTemplate restTemplate = new RestTemplate();

        // JSON values
        Map<String, String> params = new HashMap<String, String>();

        // HTTP request object
        HttpRequest request = null;
        // HTTP response onject
        HttpResponse response = null;
        String result;

        // Simple HTTP GET commands
        ResponseEntity<Student> string = restTemplate.getForEntity(endPointGetOne, Student.class, "Bob");
        System.out.println(string.getHeaders());
        Object[] results = restTemplate.getForObject(endPointGetAll, Object[].class);
        System.out.println(results);

        // Simple HTTP POST command
        Student newStudent = new Student(4000, "Donny", "don@email.com");
        ResponseEntity<String> entity = restTemplate.postForEntity(endPointSave, newStudent, String.class, params);
        System.out.println(entity.getBody().toString());

        // Put
        params.put("id", "3");
        Student updatedStudents = new Student(1, "Dolly", "test@email.com");
        restTemplate.put(endPointUpdateId, updatedStudents, params);

        // Delete
        params.put("id", "9");
        restTemplate.delete("http://localhost:8080/students/del/{id}", params);


        // Managing HttpHeaders
        HttpHeaders headers = new HttpHeaders();

        // Preparing the headers
        headers.set("MyRESTtest", "REST TEST");
        // Client wants to send request in JSON format
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // Client expects response in JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);

        // The response is a set of strings
        HttpEntity<String> entit = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> respo = restTemplate.exchange(endPointGetAll, HttpMethod.GET, entit, String.class);
        HttpStatus statusCode = respo.getStatusCode();
        System.out.println("Status code: " + statusCode);

        // Alternative to restTemplate: HttpClientBuilder
        // Send request and headers with GET method, then consume the response
        // response = HttpClientBuilder.create().build().execute(request);
    }
}
