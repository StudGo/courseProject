package ru.roman.courseproject.security;

import ru.roman.courseproject.dto.UserDTO;
import ru.roman.courseproject.models.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);

    User findUserByUsername(String username);

    List<UserDTO> findAllUsers();
}
