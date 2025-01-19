package chispa.chispa.services;

import chispa.chispa.models.Blocks;

import java.util.List;

public interface BlockService {
    Blocks findById(Long id);

    Blocks save(Blocks block);

    Blocks update(Long id, Blocks block);

    void deleteById(Long id);

    List<Blocks> findAll();

    List<Blocks> findByReporterId(Long reporterId);

    Long countTotalBlocks();
}
