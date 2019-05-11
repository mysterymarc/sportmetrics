package pl.mm.sportmetrics.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.model.database.Competition;
import pl.mm.sportmetrics.model.database.PartialResult;
import pl.mm.sportmetrics.model.database.TotalResult;

import java.util.List;

public interface PartialResultRepository extends CrudRepository<PartialResult,Long> {

    List<PartialResult> findByTotalResultId(Long totalResultId);
}
