package pl.mm.sportmetrics.repository.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.repository.entity.CompetitorEntity;

public interface CompetitorDAO extends CrudRepository<CompetitorEntity,Long> {
}
