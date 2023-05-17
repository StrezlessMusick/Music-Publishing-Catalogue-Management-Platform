package com.project38.appuser.auth;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor(force = true)
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
