package chispa.chispa.controllers;

import chispa.chispa.dtos.BlocksRequestDTO;
import chispa.chispa.dtos.BlocksResponseDTO;
import chispa.chispa.mappers.BlocksMapper;
import chispa.chispa.models.Blocks;
import chispa.chispa.services.BlocksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Block operations via REST API.
 * Handles CRUD operations and queries for user blocks.
 */
@RestController
@RequestMapping("/api/blocks")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class BlocksController {
    // Service for Block business logic
    private final BlocksService blocksService;
    // Mapper for converting between entity and DTO
    private final BlocksMapper blocksMapper;

    /**
     * Get all blocks in the system.
     *
     * @return List of BlocksResponseDTO
     */
    @GetMapping("")
    public ResponseEntity<List<BlocksResponseDTO>> getAllBlocks(
    ) {
        log.info("getAllBlocks");
        return ResponseEntity.ok(
                blocksMapper.toResponse(blocksService.findAll())
        );
    }

    /**
     * Get a block by its ID.
     * @param id Block ID
     * @return BlocksResponseDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<BlocksResponseDTO> getBlocksById(
            @PathVariable Long id
    ) {
        log.info("getBlocksById");
        return ResponseEntity.ok(
                blocksMapper.toResponse(blocksService.findById(id))
        );
    }

    /**
     * Create a new block.
     * @param blocksRequestDto Block data
     * @return Created BlocksResponseDTO
     */
    @PostMapping
    public ResponseEntity<BlocksResponseDTO> postBlocks(
            @RequestBody BlocksRequestDTO blocksRequestDto
    ) {
        log.info("addBlocks");
        Blocks blocksSaved = blocksService.save(blocksMapper.toModel(blocksRequestDto));
        return ResponseEntity.created(null).body(
                blocksMapper.toResponse(blocksSaved)
        );
    }

    /**
     * Unblock a user by block ID.
     * @param id Block ID
     */
    @PutMapping("/unblock/{id}")
    public ResponseEntity<Void> unblockUser(
            @PathVariable Long id
    ) {
        log.info("Unblocking block ID: {}", id);
        blocksService.unblock(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Update a block by its ID.
     * @param id Block ID
     * @param blocksRequestDto Updated data
     * @return Updated BlocksResponseDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<BlocksResponseDTO> putBlocks(
            @PathVariable Long id,
            @RequestBody BlocksRequestDTO blocksRequestDto
    ) {
        log.info("putBlocks");
        Blocks blockUpdated = blocksService.update(id, blocksMapper.toModel(blocksRequestDto));
        return ResponseEntity.ok(
                blocksMapper.toResponse(blockUpdated)
        );
    }

    /**
     * Delete a block by its ID and related reporter/reported IDs.
     * @param id Block ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<BlocksResponseDTO> deleteBlocks(@PathVariable Long id) {
        log.info("deleteBlocks");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        var block = blocksService.findById(id);
        if (!block.getReporter().getEmail().equals(username) && !block.getReported().getEmail().equals(username) && !authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).build();
        }
        blocksService.deleteByReportedId(id);
        blocksService.deleteByReporterId(id);
        blocksService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Patch a block by its ID.
     * @param id Block ID
     * @param blocksRequestDto Partial data
     * @return Patched BlocksResponseDTO
     */
    @PatchMapping("/patch/{id}")
    public ResponseEntity<BlocksResponseDTO> patchBlocks(
            @PathVariable Long id,
            @RequestBody BlocksRequestDTO blocksRequestDto
    ) {
        log.info("patchGeneralBlocks");
        Blocks blockPatched = blocksService.patch(id, blocksMapper.toModel(blocksRequestDto));
        return ResponseEntity.ok(blocksMapper.toResponse(blockPatched));
    }

    /**
     * Get all blocks by reporter user ID.
     * @param reporterId Reporter user ID
     * @return List of BlocksResponseDTO
     */
    @GetMapping("/reporter/{reporterId}")
    public ResponseEntity<List<BlocksResponseDTO>> getBlocksByReporterId(
            @PathVariable Long reporterId
    ) {
        return ResponseEntity.ok(
                blocksMapper.toResponse(blocksService.findByReporterId(reporterId))
        );
    }

    /**
     * Get all blocks by reported user ID.
     * @param reportedId Reported user ID
     * @return List of BlocksResponseDTO
     */
    @GetMapping("/reported/{reportedId}")
    public ResponseEntity<List<BlocksResponseDTO>> getBlocksByReportedId(
            @PathVariable Long reportedId
    ) {
        return ResponseEntity.ok(
                blocksMapper.toResponse(blocksService.findByReportedId(reportedId))
        );
    }

    /**
     * Count blocks by reporter user ID.
     * @param reporterId Reporter user ID
     * @return Number of blocks
     */
    @GetMapping("/countBlocks/reporter/{reporterId}")
    public ResponseEntity<Long> countBlocksByReporterId(
            @PathVariable Long reporterId
    ) {
        log.info("countBlocksByReporterId");
        return ResponseEntity.ok(
                blocksService.countTotalBlocksByReporterId(reporterId));
    }

    /**
     * Count blocks by reported user ID.
     * @param recipeId Reported user ID
     * @return Number of blocks
     */
    @GetMapping("/countBlocks/reported/{recipeId}")
    public ResponseEntity<Long> countBlocksByReportedId(
            @PathVariable Long recipeId
    ) {
        log.info("countBlocksByRecipeId");
        return ResponseEntity.ok(
                blocksService.countTotalBlocksByReportedId(recipeId));
    }
}
