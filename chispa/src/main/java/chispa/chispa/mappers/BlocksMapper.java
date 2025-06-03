package chispa.chispa.mappers;

import chispa.chispa.dtos.BlocksRequestDTO;
import chispa.chispa.dtos.BlocksResponseDTO;
import chispa.chispa.models.Blocks;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Mapper class for converting between Blocks entity and DTOs.
 */
@Component
public class BlocksMapper {

    /**
     * Converts a Blocks entity to a BlocksResponseDTO.
     *
     * @param block the Blocks entity to convert
     * @return the corresponding BlocksResponseDTO
     */
    public BlocksResponseDTO toResponse(Blocks block) {
        // Map entity fields to response DTO
        return new BlocksResponseDTO(
                block.getId(),
                block.getReporter(),
                block.getReported(),
                block.getBlockDate(),
                block.getBlockReason()
        );
    }

    /**
     * Converts a list of Blocks entities to a list of BlocksResponseDTOs.
     * @param blocks list of Blocks entities
     * @return list of BlocksResponseDTOs
     */
    public List<BlocksResponseDTO> toResponse(List<Blocks> blocks) {
        // Stream the list and map each Blocks entity to DTO
        return blocks.stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Converts a BlocksRequestDTO to a Blocks entity.
     * @param blockRequestDto the request DTO to convert
     * @return the new Blocks entity
     */
    public Blocks toModel(BlocksRequestDTO blockRequestDto) {
        // Create new Blocks entity with current timestamp
        return new Blocks(
                null,
                blockRequestDto.getReporter(),
                blockRequestDto.getReported(),
                LocalDateTime.now(),
                blockRequestDto.getBlockReason()
        );
    }
}
