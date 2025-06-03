package chispa.chispa.models.enums;

/**
 * Enum representing the preferred type of relationship a user is looking for.
 * Each value describes a different relationship preference.
 */
public enum PreferredRelationship {
    /**
     * Friendship relationship
     */
    FRIENDSHIP,    // Amistad
    /** Casual or non-committed relationship */
    CASUAL,        // Relación casual / sin compromiso
    /** Serious or committed relationship */
    SERIOUS,       // Relación seria / comprometida
    /** Long-term relationship */
    LONG_TERM,     // Relación a largo plazo
    /** Open relationship */
    OPEN,          // Relación abierta
    /** Only casual encounters, no commitment */
    HOOKUP,        // Solo encuentros casuales / sin compromiso
    /** Looking for marriage */
    MARRIAGE,      // Búsqueda de matrimonio
    /** Not sure or undefined */
    NOT_SURE       // No estoy seguro / indefinido
}

