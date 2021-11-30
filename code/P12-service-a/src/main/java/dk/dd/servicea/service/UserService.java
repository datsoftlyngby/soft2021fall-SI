package dk.dd.servicea.service;

import dk.dd.servicea.dto.UserDTO;

public interface UserService
{
      Long createUser(UserDTO userDto);
      void updateUser(UserDTO userDto);
}
