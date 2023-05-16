package com.formula38.appuser.security;

public class ApplicationUserPermission {



    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
