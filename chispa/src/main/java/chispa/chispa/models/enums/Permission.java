package chispa.chispa.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    CLIENT_READ("management:read"),
    CLIENT_UPDATE("management:update"),
    CLIENT_CREATE("management:create"),
    CLIENT_DELETE("management:delete"),

    USER_READ("user:read");

    private final String permission;
}