package dk.dd.graphqldemo.controller;

import dk.dd.graphqldemo.entity.User;
import dk.dd.graphqldemo.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserQuery implements GraphQLQueryResolver
{
            @Autowired
            private UserService userService;
            
            public List<User> getUsers(final int count)
            {
                  return this.userService.getAllUsers(count);
            }
            
            public Optional<User> getUser(final long id)
            {
                  return this.userService.getUser(id);
            }
}
