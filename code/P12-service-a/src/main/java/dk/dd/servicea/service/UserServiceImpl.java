package dk.dd.servicea.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.dd.servicea.dto.UserDTO;
import dk.dd.servicea.entity.User;
import dk.dd.servicea.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService
{
      private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
      private static final String TOPIC = "user-event";
      
      @Autowired
      private UserRepository userRepo;
      
      @Autowired
      private KafkaTemplate<Long, String> kafkaTemplate;
      
      @Override
      public Long createUser(UserDTO userDto)
      {
            User user = new User();
            user.setId(userDto.getId());
            user.setFirstname(userDto.getFirstname());
            System.out.println("Hello " + user.getFirstname()  );
            user.setLastname(userDto.getLastname());
            user.setEmail(userDto.getEmail());
            return this.userRepo.save(user).getId();
      }
      
      @Override
      @Transactional
      public void updateUser(UserDTO userDto)
      {
            this.userRepo.findById(userDto.getId())
                    .ifPresent(users -> {
                          users.setFirstname(userDto.getFirstname());
                          users.setLastname(userDto.getLastname());
                          users.setEmail(userDto.getEmail());
                          this.raiseEvent(userDto);
                    });
      }
      
      public void raiseEvent(UserDTO userDto)
      {
            try
            {
                  String value = OBJECT_MAPPER.writeValueAsString(userDto);
                  // this.kafkaTemplate.sendDefault(dto.getId(), value);
                  this.kafkaTemplate.send(TOPIC, value);
            }
        catch (Exception e)
            {
                  e.printStackTrace();
            }
      }
}
