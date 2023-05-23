package com.project38.authbackend.service;

import com.project38.authbackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user details service");

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }

    public UserService(PasswordEncoder encoder,
                       UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
}
