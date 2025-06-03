package chispa.chispa.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum representing the permissions available in the system.
 * Each permission is associated with a string value for security checks.
 */
@Getter
@RequiredArgsConstructor
public enum Permission {
    /**
     * Permission to read admin data
     */
    ADMIN_READ("admin:read"),
    /** Permission to update admin data */
    ADMIN_UPDATE("admin:update"),
    /** Permission to create admin data */
    ADMIN_CREATE("admin:create"),
    /** Permission to delete admin data */
    ADMIN_DELETE("admin:delete"),
    /** Permission to read client data */
    CLIENT_READ("management:read"),
    /** Permission to update client data */
    CLIENT_UPDATE("management:update"),
    /** Permission to create client data */
    CLIENT_CREATE("management:create"),
    /** Permission to delete client data */
    CLIENT_DELETE("management:delete"),
    /** Permission to read user data */
    USER_READ("user:read");

    /** String value of the permission for security checks */
    private final String permission;
}

