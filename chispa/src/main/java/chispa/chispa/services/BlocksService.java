package chispa.chispa.services;

import chispa.chispa.models.Blocks;

import java.util.List;

public interface BlocksService {
    Blocks findById(Long id);

    Blocks save(Blocks block);

    Blocks update(Long id, Blocks block);

    void deleteById(Long id);

    List<Blocks> findAll();

    Blocks patch(Long id, Blocks block);

    Long countBlocks();

    List<Blocks> findByReporterId(Long reporterId);

    Long countTotalBlocksByReporterId(Long reporterId);

    Long countTotalBlocksByReportedId(Long reportedId);

    List<Blocks> findByReportedId(Long reportedId);
}
