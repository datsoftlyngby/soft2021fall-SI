package dk.dd.rest.level1.controller;

import dk.dd.rest.level1.model.*;
import dk.dd.rest.level1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController
{
    @Autowired
    private StudentService service;
    private Object Student;

    @GetMapping("/all")
    public List<Student> retrieveAllStudents() {
        return service.findAll();
    }

    @GetMapping("/getByName/{name}")
    public Student findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @PostMapping("/save")
    public String saveStudent(@RequestBody Student student)
    {
        service.save(student);
        return " record saved..";
    }

    @DeleteMapping("/del/{id}")
    public String deleteStudent(@PathVariable int id) {
        service.deleteById(id);
        return " record deleted...";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable int id)
    {
        Optional<Student> found = service.findById(id);

        if (!found.isPresent())
            return ResponseEntity.badRequest()
                    .header("Custom-Header", "foo")
                    .body("Try again")
                    .notFound().build();
        student.setId(id);
        service.save(student);
        return ResponseEntity.ok()
                .header("Custom-Header", "foo")
                .body("Notice the custom header")
                .noContent().build();
    }
}
