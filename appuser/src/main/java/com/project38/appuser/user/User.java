package com.project38.appuser.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class User {

    private final UUID userId;
    private final String username;
}
