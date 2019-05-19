package pl.mm.sportmetrics.repository.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.repository.entity.Segment;

import java.util.List;

public interface SegmentDAO extends CrudRepository<Segment,Long> {

    List<Segment> findByCompetitionId(Long CompetitionId);
}
