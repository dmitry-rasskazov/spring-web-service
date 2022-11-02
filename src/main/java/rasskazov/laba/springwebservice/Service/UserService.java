package rasskazov.laba.springwebservice.Service;

import org.springframework.stereotype.Service;
import rasskazov.laba.springwebservice.Dto.UserDto;
import rasskazov.laba.springwebservice.Entity.User;

import java.util.List;

public interface UserService
{
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
