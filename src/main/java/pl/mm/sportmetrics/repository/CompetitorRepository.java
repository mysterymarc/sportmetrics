package pl.mm.sportmetrics.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.model.database.Competition;
import pl.mm.sportmetrics.model.database.Competitor;

public interface CompetitorRepository extends CrudRepository<Competitor,Long> {
}
