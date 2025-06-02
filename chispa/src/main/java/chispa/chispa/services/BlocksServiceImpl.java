package chispa.chispa.services;

import chispa.chispa.models.Blocks;
import chispa.chispa.models.Messages;
import chispa.chispa.models.enums.LikeState;
import chispa.chispa.models.enums.MatchState;
import chispa.chispa.models.enums.MessageState;
import chispa.chispa.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BlocksServiceImpl implements BlocksService {
    private final BlocksRepository blocksRepository;
    private final UsersDetailsRepository usersRepository;
    private final MatchesRepository matchesRepository;
    private final LikesRepository likesRepository;
    private final MessagesRepository messagesRepository;

    @Override
    public List<Blocks> findAll() {
        return blocksRepository.findAll();
    }

    @Override
    public Blocks findById(Long id) {
        return blocksRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Blocks not found"));
    }

    @Override
    public Blocks save(Blocks block) {
        block.setReporter(usersRepository.findById(block.getReporter().getId()).orElseThrow());
        block.setReported(usersRepository.findById(block.getReported().getId()).orElseThrow());

        Blocks savedBlock = blocksRepository.save(block);

        Long reporterId = block.getReporter().getId();
        Long reportedId = block.getReported().getId();

        matchesRepository.findMatchesByUser1IdAndUser2Id(reporterId, reportedId).ifPresent(match -> {
            match.setMatchState(MatchState.BLOCKED);
            matchesRepository.save(match);
        });

        likesRepository.findByLikerIdAndLikedId(reporterId, reportedId).ifPresent(like -> {
            like.setState(LikeState.BLOCKED);
            likesRepository.save(like);
        });

        likesRepository.findByLikerIdAndLikedId(reportedId, reporterId).ifPresent(like -> {
            like.setState(LikeState.BLOCKED);
            likesRepository.save(like);
        });

        List<Messages> messages1 = messagesRepository.findBySenderUserIdAndReceiverUserId(reporterId, reportedId);
        List<Messages> messages2 = messagesRepository.findBySenderUserIdAndReceiverUserId(reportedId, reporterId);

        messages1.forEach(msg -> msg.setMessageState(MessageState.BLOCKED));
        messages2.forEach(msg -> msg.setMessageState(MessageState.BLOCKED));

        messages1.addAll(messages2);
        messagesRepository.saveAll(messages1);

        return savedBlock;
    }

    @Override
    public void unblock(Long blockId) {
        Blocks block = this.findById(blockId);
        Long reporterId = block.getReporter().getId();
        Long reportedId = block.getReported().getId();

        // Restaurar Match
        matchesRepository.findMatchesByUser1IdAndUser2Id(reporterId, reportedId).ifPresent(match -> {
            match.setMatchState(MatchState.MATCHED);
            matchesRepository.save(match);
        });

        // Restaurar Likes
        likesRepository.findByLikerIdAndLikedId(reporterId, reportedId).ifPresent(like -> {
            like.setState(LikeState.LIKED);
            likesRepository.save(like);
        });

        likesRepository.findByLikerIdAndLikedId(reportedId, reporterId).ifPresent(like -> {
            like.setState(LikeState.LIKED);
            likesRepository.save(like);
        });

        List<Messages> messages1 = messagesRepository.findBySenderUserIdAndReceiverUserId(reporterId, reportedId);
        List<Messages> messages2 = messagesRepository.findBySenderUserIdAndReceiverUserId(reportedId, reporterId);

        messages1.forEach(msg -> msg.setMessageState(MessageState.SEND));
        messages2.forEach(msg -> msg.setMessageState(MessageState.SEND));
        messages1.addAll(messages2);
        messagesRepository.saveAll(messages1);

        blocksRepository.deleteById(blockId);
    }

    @Override
    public Blocks update(Long id, Blocks block) {
        Blocks updated = this.findById(id);
        updated.setReporter(block.getReporter());
        updated.setReported(block.getReported());
        updated.setBlockDate(LocalDateTime.now());
        updated.setBlockReason(block.getBlockReason());
        return blocksRepository.save(updated);
    }

    @Override
    public void deleteById(Long id) {
        blocksRepository.deleteById(id);
    }

    @Override
    public void deleteByReporterId(Long reporterId) {
        blocksRepository.deleteByReporterId(reporterId);
    }

    @Override
    public void deleteByReportedId(Long reportedId) {
        blocksRepository.deleteByReportedId(reportedId);
    }

    @Override
    public Blocks patch(Long id, Blocks block) {
        Blocks blockToPatch = blocksRepository.findById(id).orElseThrow();
        if (block.getReporter() != null) {
            blockToPatch.setReporter(block.getReporter());
        }
        if (block.getReported() != null) {
            blockToPatch.setReported(block.getReported());
        }
        if (block.getBlockDate() != null) {
            blockToPatch.setBlockDate(LocalDateTime.now());
        }
        if (block.getBlockReason() != null) {
            blockToPatch.setBlockReason(block.getBlockReason());
        }
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
