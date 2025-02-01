package chispa.chispa.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static chispa.chispa.models.enums.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {
    CLIENT(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    CLIENT_READ,
                    CLIENT_UPDATE,
                    CLIENT_DELETE,
                    CLIENT_CREATE
            )
    ),
    USER(
            Set.of(
                    CLIENT_READ,
                    CLIENT_UPDATE,
                    CLIENT_DELETE,
                    CLIENT_CREATE
            )
    );

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority(this.name()));
        return authorities;
    }
}