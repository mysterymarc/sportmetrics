package pl.mm.sportmetrics.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.model.database.Competition;

public interface CompetitionRepository extends CrudRepository<Competition,Long> {
}
