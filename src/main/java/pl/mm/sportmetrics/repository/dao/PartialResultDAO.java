package pl.mm.sportmetrics.repository.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mm.sportmetrics.repository.entity.PartialResultEntity;

import java.util.List;
import java.util.Optional;

public interface PartialResultDAO extends CrudRepository<PartialResultEntity,Long> {

    Optional<List<PartialResultEntity>> findByTotalResultId(Long totalResultId);
}
