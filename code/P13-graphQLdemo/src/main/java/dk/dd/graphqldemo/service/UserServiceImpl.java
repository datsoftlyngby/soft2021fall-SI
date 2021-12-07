package dk.dd.graphqldemo.service;

import dk.dd.graphqldemo.entity.User;
import dk.dd.graphqldemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
        @Autowired
        private UserRepository userRepo;
        
            @Transactional(readOnly = true)
            public Optional<User> getUser(final long id)
            {
                  return this.userRepo.findById((long) id);
            }
            
            @Transactional(readOnly = true)
            public List<User> getAllUsers(final int count)
            {
                  return this.userRepo.findAll().stream().limit(count).collect(Collectors.toList());
            }
            
      @Transactional
            public User createUser(String firstname, String lastname, String email)
            {
                  User user = new User();
                  user.setFirstname(firstname);
                  user.setLastname(lastname);
                  user.setEmail(email);
                  return this.userRepo.save(user);
            }
}
