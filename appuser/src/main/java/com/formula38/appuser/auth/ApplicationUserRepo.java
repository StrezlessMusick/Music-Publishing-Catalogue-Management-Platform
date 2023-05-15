package com.formula38.appuser.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationUserRepo extends JpaRepository<ApplicationUser, Long> {

    // Selecting an application user by username using applicationUsers to verify
    default Optional<ApplicationUser> selectApplicationUserByUsername(String username){
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    List<ApplicationUser> getApplicationUsers();

    // Select all Application Users and Save to applicationUsers var
//    @Query(value = "select * from application_user", nativeQuery = true)
//    private List<ApplicationUser> getApplicationUsers() {
//        List<ApplicationUser> applicationUsers;
//
//        return null;
//    }
}
