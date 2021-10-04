package dk.dd.msaassignments.controller;

import dk.dd.msaassignments.model.*;
import dk.dd.msaassignments.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/tasks")
public class TaskController
{
      @Autowired
      private TaskRepository repo;
      
      @GetMapping("/all")
      public List<Task> retrieveAllTasks()
      {
            return repo.findAll();
      }

      @GetMapping("/{id}")
      public EntityModel<Task> retrieveStudent(@PathVariable long id)
      {
            Optional<Task> task = repo.findById(id);
           // if (!student.isPresent())
                  //throw new StudentNotFoundException("id: " + id);
            
            EntityModel<Task> resource = EntityModel.of(task.get()); 		// get the resource
            WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllTasks()); // get link
            resource.add(linkTo.withRel("all-tasks"));										// append the link
            
            Link selfLink = linkTo(methodOn(this.getClass()).retrieveStudent(id)).withSelfRel(); //add also link to self
            resource.add(selfLink);
            return resource;
      }
      
      @GetMapping("/type/{type}")
      public Object findByType(@PathVariable  String type)
      {
            return repo.findByType(type);
      }
}
