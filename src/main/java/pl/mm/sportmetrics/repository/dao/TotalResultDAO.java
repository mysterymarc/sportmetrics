package pl.mm.sportmetrics.repository.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.repository.entity.TotalResult;

import java.util.List;

public interface TotalResultDAO extends CrudRepository<TotalResult,Long> {

    List<TotalResult> findByCompetitionId(Long CompetitionId);
}
