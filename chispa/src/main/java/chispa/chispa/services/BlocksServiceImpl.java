package chispa.chispa.services;

import chispa.chispa.models.Blocks;
import chispa.chispa.repositories.BlocksRepository;
import chispa.chispa.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BlocksServiceImpl implements BlocksService {
    private final BlocksRepository blocksRepository;
    private final UsersRepository usersRepository;

    @Override
    public List<Blocks> findAll() {
        return blocksRepository.findAll();
    }
    @Override
    public Blocks findById(Long id) {
        return blocksRepository.findById(id).orElseThrow();
    }
    @Override
    public void deleteById(Long id) {
        blocksRepository.deleteById(id);
    }
    @Override
    public Blocks save(Blocks block) {
        block.setReporter(usersRepository.findById(block.getReporter().getId()).get());
        block.setReported(usersRepository.findById(block.getReported().getId()).get());
        return blocksRepository.save(block);
    }
    @Override
    public Blocks update(Long id, Blocks block) {
        Blocks updated = this.findById(id);
        updated.setReporter(block.getReporter());
        updated.setReported(block.getReported());
        updated.setBlockDate(LocalDate.now());
        updated.setBlockReason(block.getBlockReason());
        return blocksRepository.save(updated);
    }
    @Override
    public Blocks patch(Long id, Blocks block) {
        Blocks blockToPatch = blocksRepository.findById(id).orElseThrow();
        if(block.getReporter() != null){blockToPatch.setReporter(block.getReporter());}
        if(block.getReported() != null){blockToPatch.setReported(block.getReported());}
        if(block.getBlockDate() != null){blockToPatch.setBlockDate(LocalDate.now());}
        if(block.getBlockReason() != null){blockToPatch.setBlockReason(block.getBlockReason());}
        return blocksRepository.save(blockToPatch);
    }
    @Override
    public Long countBlocks() {
        return blocksRepository.count();
    }
    @Override
    public Long countTotalBlocksByReporterId(Long reporterId) {
        return blocksRepository.countBlocksByReporterId(reporterId);
    }
    @Override
    public Long countTotalBlocksByReportedId(Long reportedId) {
        return blocksRepository.countBlocksByReportedId(reportedId);
    }
    @Override
    public List<Blocks> findByReporterId(Long reporterId) {
        return blocksRepository.findByReporterId(reporterId);
    }
    @Override
    public List<Blocks> findByReportedId(Long reportedId) {
        return blocksRepository.findByReportedId(reportedId);
    }
}
