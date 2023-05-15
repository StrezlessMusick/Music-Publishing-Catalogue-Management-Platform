package com.formula38.appuser.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationUserRepo extends JpaRepository<ApplicationUser, Long> {
}
