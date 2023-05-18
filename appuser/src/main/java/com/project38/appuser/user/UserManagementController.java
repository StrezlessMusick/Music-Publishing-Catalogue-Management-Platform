package com.project38.appuser.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/users")
public class UserManagementController {

    private static final List<User> USERS = Arrays.asList(
//            new User(1, "James Bond"),
//            new User(2, "Maria Jones"),
//            new User(3, "Anna Smith")
    );

    // hasRole('Role_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<User> getAllStudents() {
        System.out.println("getAllUsers");
        return USERS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody User user) {
        System.out.println("registerNewStudent");
        System.out.println(user);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer userId) {
        System.out.println("deleteStudent");
        System.out.println(userId);
    }

    @PostMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer userId,@RequestBody User user) {
        System.out.println("updateStudent");
        System.out.println(String.format("%s %s", userId, user));
    }
}
