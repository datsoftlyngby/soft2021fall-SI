package dk.dd.msareport.controller;

import dk.dd.msareport.model.Report;
import dk.dd.msareport.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController
{
      @Autowired
      private ReportRepository rep;
      
      @GetMapping("/all")
      public List<Report> retrieveAllTasks()
      {
            return rep.findAll();
      }
      
      @PostMapping("/report")
      public Report save(@RequestBody Report report)
      {
            Report result = rep.save(report);
            return result;
      }
      
      @GetMapping("/{id}}")
      public Report getOne(@PathVariable long id)
      {
            return rep.findById(id);
      }

      }
