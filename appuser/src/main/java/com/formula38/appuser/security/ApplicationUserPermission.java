package com.formula38.appuser.security;

public enum ApplicationUserPermission {

    ARTIST_READ("artist:read"),
    ARTIST_WRITE("artist:write"),

    TRACK_READ("track:read"),
    TRACK_WRITE("track:write"),

    PROJECT_READ("project:read"),
    PROJECT_WRITE("project:write"),

    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
