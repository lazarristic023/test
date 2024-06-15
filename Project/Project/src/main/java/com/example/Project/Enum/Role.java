package com.example.Project.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    CLIENT(
            Set.of(
                    Permission.CLIENT_READ,
                    Permission.CLIENT_UPDATE,
                    Permission.CLIENT_DELETE,
                    Permission.CLIENT_CREATE
            )),
    EMPLOYEE(
            Set.of(
                    Permission.EMPLOYEE_READ,
                    Permission.EMPLOYEE_UPDATE,
                    Permission.EMPLOYEE_DELETE,
                    Permission.EMPLOYEE_CREATE
            )
    ),
    ADMINISTRATOR(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_DELETE,
                    Permission.ADMIN_CREATE,
                    Permission.EMPLOYEE_READ,
                    Permission.EMPLOYEE_DELETE,
                    Permission.EMPLOYEE_CREATE,
                    Permission.CLIENT_READ
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}