package chispa.chispa.services;

import chispa.chispa.models.Blocks;

import java.util.List;

public interface BlocksService {
    Blocks findById(Long id);

    Blocks save(Blocks block);

    void unblock(Long blockId);

    Blocks update(Long id, Blocks block);

    void deleteById(Long id);

    void deleteByReporterId(Long reporterId);

    void deleteByReportedId(Long reportedId);

    List<Blocks> findAll();

    Blocks patch(Long id, Blocks block);

    Long countBlocks();

    List<Blocks> findByReporterId(Long reporterId);

    Long countTotalBlocksByReporterId(Long reporterId);

    Long countTotalBlocksByReportedId(Long reportedId);

    List<Blocks> findByReportedId(Long reportedId);


}
