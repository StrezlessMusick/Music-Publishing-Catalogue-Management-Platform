package com.project38.appuser.auth;

import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project38.appuser.security.ApplicationUserRole.PUBLISHER;

@Service
@NoArgsConstructor(force = true)
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepo applicationUserRepo;
    private final PasswordEncoder passwordEncoder;
    public ApplicationUserService(ApplicationUserRepo applicationUserRepo, PasswordEncoder passwordEncoder) {
        this.applicationUserRepo = applicationUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserRepo
                .selectApplicationUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format("Username %s not found", username)
                ));
    }

    public List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "annasmith",
                        passwordEncoder.encode("password"),
                        PUBLISHER.grantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "linda",
                        passwordEncoder.encode("password"),
                        ADMIN.grantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "tom",
                        passwordEncoder.encode("password"),
                        ADMINTRAINEE.grantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }
}
