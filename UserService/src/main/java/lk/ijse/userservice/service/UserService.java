package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.User;

import java.util.List;

public interface UserService {
    int saveUser(UserDTO user);

    int updateUser(UserDTO user);

    int deleteUser(String email);

    List<UserDTO> getAll();

    UserDTO getUserByEmail(String email);
}
