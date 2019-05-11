package pl.mm.sportmetrics.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.model.database.Competition;
import pl.mm.sportmetrics.model.database.Segment;
import pl.mm.sportmetrics.model.database.TotalResult;

import java.util.List;

public interface TotalResultRepository extends CrudRepository<TotalResult,Long> {

    List<TotalResult> findByCompetitionId(Long CompetitionId);
}
