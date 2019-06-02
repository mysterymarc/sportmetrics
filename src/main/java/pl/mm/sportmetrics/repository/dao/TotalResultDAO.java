package pl.mm.sportmetrics.repository.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.repository.entity.TotalResultEntity;

import java.util.List;
import java.util.Optional;

public interface TotalResultDAO extends CrudRepository<TotalResultEntity,Long> {

    Optional<List<TotalResultEntity>> findByCompetitionId(Long CompetitionId);
}
