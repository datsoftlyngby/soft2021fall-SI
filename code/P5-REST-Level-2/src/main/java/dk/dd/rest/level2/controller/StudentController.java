package dk.dd.rest.level2.controller;
/*
 * Maturity Level 2: Share operations across resources
 * Add model attributes validation and exception handling
 */

import dk.dd.rest.level2.exceptions.ResourceNotFoundException;
import dk.dd.rest.level2.model.Student;
import dk.dd.rest.level2.repository.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService repo;
    private Object Student;

    @GetMapping("/")
    public List<Student> retrieveAllStudents() {
        return (List<Student>) repo.findAll();
    }

    @GetMapping("/{name}")
    public Student findByName(@PathVariable(name = "name") String name) throws ResourceNotFoundException {
        return repo.findByName(name);
    }

    @PostMapping("/")
    public String saveStudent(@Valid @RequestBody Student student)
    {
        repo.save(student);
        return " record saved..";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        repo.deleteById(id);
        return " record deleted...";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@Valid @RequestBody Student student, @PathVariable(value = "id") int id) throws ResourceNotFoundException {
        Optional<Student> found = repo.findById(id);

        if (!found.isPresent())
            return ResponseEntity.badRequest()
                    .header("Custom-Header", "foo")
                    .body("Try again")
                    .notFound().build();
        student.setId(id);
        repo.save(student);
        return ResponseEntity.ok()
                .header("Custom-Header", "foo")
                .body("Notice the custom header")
                .noContent().build();
    }
}
