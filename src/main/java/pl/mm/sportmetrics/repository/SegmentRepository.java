package pl.mm.sportmetrics.repository;

import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.domain.model.Segments;
import pl.mm.sportmetrics.repository.dao.SegmentDAO;
import pl.mm.sportmetrics.repository.entity.EventEntitiesSet;

@Service
public class SegmentRepository {

    private final SegmentDAO segmentDao;

    public SegmentRepository(SegmentDAO segmentDao) {
        this.segmentDao = segmentDao;
    }

    public Segments findAllSegments(Long competitionId){
        Segments segments = new Segments();
        segmentDao.findByCompetitionId(competitionId)
                .orElseThrow(()-> new IllegalArgumentException("Repository doesn't return Segments for competition_id=" + competitionId))
                .forEach(segment -> segments.add(segment.name));
        return segments;
    }

    public void saveSegments(EventEntitiesSet event){
        segmentDao.saveAll(event.segments);
    }
}
