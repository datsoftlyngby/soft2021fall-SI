package dk.dd.servicea.controller;

import dk.dd.servicea.dto.UserDTO;
import dk.dd.servicea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController
{
            @Autowired
            private UserService myService;
            
            @PostMapping("/")
            public Long createUser(@RequestBody UserDTO userDto)
            {
                  System.out.println(userDto);
                  return this.myService.createUser(userDto);
            }
            
            @PutMapping("/")
            public void updateUser(@RequestBody UserDTO userDto)
            {
                  this.myService.updateUser(userDto);
            }
}
