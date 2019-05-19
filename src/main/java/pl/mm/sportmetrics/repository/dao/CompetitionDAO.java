package pl.mm.sportmetrics.repository.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.repository.entity.Competition;

public interface CompetitionDAO extends CrudRepository<Competition,Long> {
}
