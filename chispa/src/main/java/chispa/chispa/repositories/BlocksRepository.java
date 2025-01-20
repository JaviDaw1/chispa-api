package chispa.chispa.repositories;

import chispa.chispa.models.Blocks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlocksRepository extends JpaRepository<Blocks, Long> {
    Long countBlocksByReporterId(Long reporterId);
    Long countBlocksByReportedId(Long reportedId);
    List<Blocks> findByReporterId(Long reporterId);
    List<Blocks> findByReportedId(Long reportedId);
}
