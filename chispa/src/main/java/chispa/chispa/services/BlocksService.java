package chispa.chispa.services;

import chispa.chispa.models.Blocks;
import java.util.List;

/**
 * Service interface for managing block operations between users.
 * Provides methods for CRUD operations and querying block relationships.
 */
public interface BlocksService {
    /**
     * Finds a block by its ID.
     *
     * @param id the block ID
     * @return the found block
     */
    Blocks findById(Long id);

    /**
     * Saves a new block relationship.
     * @param block the block to save
     * @return the saved block
     */
    Blocks save(Blocks block);

    /**
     * Removes a block between users.
     * @param blockId the ID of the block to remove
     */
    void unblock(Long blockId);

    /**
     * Updates an existing block.
     * @param id the ID of the block to update
     * @param block the updated block data
     * @return the updated block
     */
    Blocks update(Long id, Blocks block);

    /**
     * Deletes a block by its ID.
     * @param id the block ID to delete
     */
    void deleteById(Long id);

    /**
     * Deletes all blocks where the given user is the reporter.
     * @param reporterId the reporter user ID
     */
    void deleteByReporterId(Long reporterId);

    /**
     * Deletes all blocks where the given user is the reported.
     * @param reportedId the reported user ID
     */
    void deleteByReportedId(Long reportedId);

    /**
     * Retrieves all blocks.
     * @return list of all blocks
     */
    List<Blocks> findAll();

    /**
     * Partially updates a block.
     * @param id the block ID to update
     * @param block the block data with fields to update
     * @return the patched block
     */
    Blocks patch(Long id, Blocks block);

    /**
     * Finds all blocks where the given user is the reporter.
     * @param reporterId the reporter user ID
     * @return list of blocks
     */
    List<Blocks> findByReporterId(Long reporterId);

    /**
     * Counts total blocks by reporter ID.
     * @param reporterId the reporter user ID
     * @return count of blocks
     */
    Long countTotalBlocksByReporterId(Long reporterId);

    /**
     * Counts total blocks by reported ID.
     * @param reportedId the reported user ID
     * @return count of blocks
     */
    Long countTotalBlocksByReportedId(Long reportedId);

    /**
     * Finds all blocks where the given user is the reported.
     * @param reportedId the reported user ID
     * @return list of blocks
     */
    List<Blocks> findByReportedId(Long reportedId);
}