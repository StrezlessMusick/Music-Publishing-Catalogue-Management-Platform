package com.formula38.appuser.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepo applicationUserRepo;
    public ApplicationUserService(ApplicationUserRepo applicationUserRepo) {
        this.applicationUserRepo = applicationUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserRepo
                .selectApplicationUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format("Username %s not found", username)
                ));
    }
}
