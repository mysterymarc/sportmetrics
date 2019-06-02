package pl.mm.sportmetrics.repository.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.repository.entity.CompetitionEntity;

public interface CompetitionDAO extends CrudRepository<CompetitionEntity,Long> {
}
