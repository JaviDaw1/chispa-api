package chispa.chispa.models.enums;

/**
 * Enum representing the state of a message in the system.
 * SEND: The message has been sent.
 * BLOCKED: The message is blocked and not delivered.
 */
public enum MessageState {
    /**
     * The message has been sent
     */
    SEND,
    /** The message is blocked and not delivered */
    BLOCKED
}
