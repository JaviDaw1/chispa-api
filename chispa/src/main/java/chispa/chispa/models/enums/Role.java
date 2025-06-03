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
/**
 * Enum representing the different roles in the system.
 * Each role can have a set of permissions.
 */
public enum Role {
    /**
     * Client role with no special permissions.
     */
    CLIENT(Collections.emptySet()),
    /**
     * Admin role with all admin and client permissions.
     */
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
    /**
     * User role with client permissions only.
     */
    USER(
            Set.of(
                    CLIENT_READ,
                    CLIENT_UPDATE,
                    CLIENT_DELETE,
                    CLIENT_CREATE
            )
    );

    /**
     * Set of permissions assigned to the role.
     */
    private final Set<Permission> permissions;

    /**
     * Returns a list of authorities (permissions) for Spring Security.
     *
     * @return List of SimpleGrantedAuthority
     */
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority(this.name()));
        return authorities;
    }
}

