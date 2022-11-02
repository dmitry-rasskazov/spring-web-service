package rasskazov.laba.springwebservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rasskazov.laba.springwebservice.Entity.User;
import rasskazov.laba.springwebservice.Repository.UserRepository;

import java.util.stream.Collectors;

@Service
public class CustomerUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;

    @Autowired
    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(usernameOrEmail);

        if(null != user) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
            );
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
