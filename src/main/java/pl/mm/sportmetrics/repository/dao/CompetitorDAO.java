package pl.mm.sportmetrics.repository.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.repository.entity.Competitor;

public interface CompetitorDAO extends CrudRepository<Competitor,Long> {
}
