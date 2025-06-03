package chispa.chispa.repositories;

import chispa.chispa.models.Blocks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for managing Block relationships between users.
 */
public interface BlocksRepository extends JpaRepository<Blocks, Long> {

    /**
     * Counts how many users the given user has blocked.
     *
     * @param reporterId ID of the user who reported.
     * @return Number of blocks made by the user.
     */
    Long countBlocksByReporterId(Long reporterId);

    /**
     * Counts how many times the given user has been blocked.
     * @param reportedId ID of the reported user.
     * @return Number of times the user was blocked.
     */
    Long countBlocksByReportedId(Long reportedId);

    /**
     * Finds all blocks made by a user.
     * @param reporterId ID of the reporter.
     * @return List of block entries.
     */
    List<Blocks> findByReporterId(Long reporterId);

    /**
     * Finds all blocks where the given user is the target.
     * @param reportedId ID of the reported user.
     * @return List of block entries.
     */
    List<Blocks> findByReportedId(Long reportedId);

    /**
     * Deletes all blocks created by a specific user.
     * @param reporterId ID of the reporter.
     */
    void deleteByReporterId(Long reporterId);

    /**
     * Deletes all blocks targeting a specific user.
     * @param reportedId ID of the reported user.
     */
    void deleteByReportedId(Long reportedId);
}
