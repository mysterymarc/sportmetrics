package pl.mm.sportmetrics.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.model.database.Competition;
import pl.mm.sportmetrics.model.database.Segment;

import java.util.List;

public interface SegmentRepository extends CrudRepository<Segment,Long> {

    List<Segment> findByCompetitionId(Long CompetitionId);
}
