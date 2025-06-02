package chispa.chispa.mappers;

import chispa.chispa.dtos.BlocksRequestDTO;
import chispa.chispa.dtos.BlocksResponseDTO;
import chispa.chispa.models.Blocks;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BlocksMapper {
    public BlocksResponseDTO toResponse(Blocks block) {
        return new BlocksResponseDTO(
                block.getId(),
                block.getReporter(),
                block.getReported(),
                block.getBlockDate(),
                block.getBlockReason()
        );
    }
    public List<BlocksResponseDTO> toResponse(List<Blocks> blocks) {
        return blocks.stream()
                .map(this::toResponse)
                .toList();
    }
    public Blocks toModel(BlocksRequestDTO blockRequestDto) {
        return new Blocks(
                null,
                blockRequestDto.getReporter(),
                blockRequestDto.getReported(),
                LocalDateTime.now(),
                blockRequestDto.getBlockReason()
        );
    }
}
