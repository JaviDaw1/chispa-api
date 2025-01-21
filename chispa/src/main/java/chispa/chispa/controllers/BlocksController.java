package chispa.chispa.controllers;

import chispa.chispa.dtos.BlocksRequestDTO;
import chispa.chispa.dtos.BlocksResponseDTO;
import chispa.chispa.mappers.BlocksMapper;
import chispa.chispa.models.Blocks;
import chispa.chispa.services.BlocksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blocks")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class BlocksController {
    private final BlocksService blocksService;
    private final BlocksMapper blocksMapper;

    @GetMapping("")
    public ResponseEntity<List<BlocksResponseDTO>> getAllBlocks(
    ) {
        log.info("getAllBlocks");

        return ResponseEntity.ok(
                blocksMapper.toResponse(blocksService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlocksResponseDTO> getBlocksById(
            @PathVariable Long id
    ) {
        log.info("getBlocksById");
        return ResponseEntity.ok(
                blocksMapper.toResponse(blocksService.findById(id))
        );
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<BlocksResponseDTO> deleteBlocks(
            @PathVariable Long id
    ) {
        log.info("deleteBlocks");
        blocksService.deleteByReportedId(id);
        blocksService.deleteByReporterId(id);
        blocksService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<BlocksResponseDTO> patchBlocks(
            @PathVariable Long id,
            @RequestBody BlocksRequestDTO blocksRequestDto
    ) {
        log.info("patchGeneralBlocks");

        Blocks blockPatched = blocksService.patch(id, blocksMapper.toModel(blocksRequestDto));

        return ResponseEntity.ok(blocksMapper.toResponse(blockPatched));
    }

    @GetMapping("/reporter/{reporterId}")
    public ResponseEntity<List<BlocksResponseDTO>> getBlocksByReporterId(
            @PathVariable Long reporterId
    ) {
        return ResponseEntity.ok(
                blocksMapper.toResponse(blocksService.findByReporterId(reporterId))
        );
    }

    @GetMapping("/reported/{reportedId}")
    public ResponseEntity<List<BlocksResponseDTO>> getBlocksByReportedId(
            @PathVariable Long reportedId
    ) {
        return ResponseEntity.ok(
                blocksMapper.toResponse(blocksService.findByReportedId(reportedId))
        );
    }

    @GetMapping("/countBlocks/reporter/{reporterId}")
    public ResponseEntity<Long> countBlocksByReporterId(
            @PathVariable Long reporterId
    ) {
        log.info("countBlocksByReporterId");
        return ResponseEntity.ok(
                blocksService.countTotalBlocksByReporterId(reporterId));
    }

    @GetMapping("/countBlocks/reported/{recipeId}")
    public ResponseEntity<Long> countBlocksByReportedId(
            @PathVariable Long recipeId
    ) {
        log.info("countBlocksByRecipeId");
        return ResponseEntity.ok(
                blocksService.countTotalBlocksByReportedId(recipeId));

    }
}
