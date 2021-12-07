package dk.dd.graphqldemo.service;

import dk.dd.graphqldemo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService
{
            Optional<User> getUser(long id);
            List<User> getAllUsers(int cnt);
            User createUser(String firstname, String lastname, String email);
}
