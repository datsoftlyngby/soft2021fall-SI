package dk.dd.graphqldemo.controller;

import dk.dd.graphqldemo.entity.User;
import dk.dd.graphqldemo.service.UserService;

import dk.dd.graphqldemo.service.UserServiceImpl;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver
{
            @Autowired
            private UserService userService;
      
            public User createUser(String firstname, String lastname, String email)
            {
                  return this.userService.createUser(firstname, lastname, email);
            }
}
