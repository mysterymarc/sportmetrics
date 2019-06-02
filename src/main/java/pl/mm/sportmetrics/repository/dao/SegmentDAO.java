package pl.mm.sportmetrics.repository.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.repository.entity.SegmentEntity;

import java.util.List;
import java.util.Optional;

public interface SegmentDAO extends CrudRepository<SegmentEntity,Long> {

    Optional<List<SegmentEntity>> findByCompetitionId(Long CompetitionId);
}
