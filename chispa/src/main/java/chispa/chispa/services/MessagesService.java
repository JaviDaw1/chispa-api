package chispa.chispa.services;

import chispa.chispa.models.Messages;
import java.util.List;

/**
 * Service interface for managing messages between users.
 * Provides methods for CRUD operations and message management.
 */
public interface MessagesService {
    /**
     * Finds a message by its ID.
     *
     * @param id the message ID
     * @return the found message
     */
    Messages findById(Long id);

    /**
     * Saves a new message.
     * @param message the message to save
     * @return the saved message
     */
    Messages save(Messages message);

    /**
     * Updates an existing message.
     * @param id the ID of the message to update
     * @param message the updated message data
     * @return the updated message
     */
    Messages update(Long id, Messages message);

    /**
     * Deletes a message by its ID.
     * @param id the message ID to delete
     */
    void deleteById(Long id);

    /**
     * Retrieves all messages.
     * @return list of all messages
     */
    List<Messages> findAll();

    /**
     * Finds all messages for a specific match.
     * @param matchId the match ID
     * @return list of messages
     */
    List<Messages> findByMatchId(Long matchId);

    /**
     * Marks a message as read.
     * @param messageId the message ID to mark as read
     * @return the updated message
     */
    Messages markAsRead(Long messageId);

    /**
     * Deletes all messages for a specific match.
     * @param matchId the match ID
     */
    void deleteMessagesByMatchId(Long matchId);
}