package rasskazov.laba.springwebservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rasskazov.laba.springwebservice.Dto.UserDto;
import rasskazov.laba.springwebservice.Entity.Role;
import rasskazov.laba.springwebservice.Entity.User;
import rasskazov.laba.springwebservice.Repository.RoleRepository;
import rasskazov.laba.springwebservice.Repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    )
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(null == role) {
            role = checkRoleExist();

        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {return userRepository.findByEmail(email);}

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> mapToDto(user)).collect(Collectors.toList());
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    private UserDto mapToDto(User user)
    {
        UserDto userDto = new UserDto();
        String[] names = user.getName().split(" ");
        userDto.setFirstName(names[0]);
        userDto.setLastName(names[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
