package pl.mm.sportmetrics.repository.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.repository.entity.PartialResult;

import java.util.List;

public interface PartialResultDAO extends CrudRepository<PartialResult,Long> {

    List<PartialResult> findByTotalResultId(Long totalResultId);
}
