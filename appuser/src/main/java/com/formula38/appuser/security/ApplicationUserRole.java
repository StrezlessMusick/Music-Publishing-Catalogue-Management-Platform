package com.formula38.appuser.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.formula38.appuser.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    ADMIN(Sets.newHashSet(
            USER_READ, USER_WRITE,
            TRACK_READ, TRACK_WRITE,
            ARTIST_READ, ARTIST_WRITE,
            PROJECT_READ, PROJECT_WRITE
    )),

    PUBLISHER(Sets.newHashSet(
            USER_READ,
            TRACK_READ, TRACK_WRITE,
            ARTIST_READ, ARTIST_WRITE,
            PROJECT_READ, PROJECT_WRITE
    )),
    PERFORMING_ARTIST(Sets.newHashSet(
            USER_READ,
            TRACK_READ,
            ARTIST_READ,
            PROJECT_READ
    )),
    ARTIST_MANAGER(Sets.newHashSet(
            USER_READ,
            TRACK_READ,
            ARTIST_READ,
            PROJECT_READ
    )),
    AUTHOR(Sets.newHashSet(
            USER_READ,
            TRACK_READ,
            ARTIST_READ,
            PROJECT_READ
    )),
    COMPOSER(Sets.newHashSet(
            USER_READ,
            TRACK_READ,
            ARTIST_READ,
            PROJECT_READ
    )),
    MUSIC_SUPERVISOR(Sets.newHashSet(
            USER_READ,
            TRACK_READ,
            ARTIST_READ,
            PROJECT_READ
    )),
    VIDEO_PRODUCER(Sets.newHashSet(
            USER_READ,
            TRACK_READ,
            ARTIST_READ,
            PROJECT_READ
    )),
    GAME_DEVELOPER(Sets.newHashSet(
            USER_READ,
            TRACK_READ,
            ARTIST_READ,
            PROJECT_READ
    ));


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> grantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
