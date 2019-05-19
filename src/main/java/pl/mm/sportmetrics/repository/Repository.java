package pl.mm.sportmetrics.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.repository.entity.Event;
import pl.mm.sportmetrics.model.inputfile.EventDataCollection;
import pl.mm.sportmetrics.repository.dao.*;
import pl.mm.sportmetrics.repository.entity.*;
import pl.mm.sportmetrics.model.repo.Segments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Repository {

    @Autowired
    private CompetitionDAO competitionDao;

    @Autowired
    private SegmentDAO segmentDao;

    @Autowired
    private CompetitorDAO competitorDao;

    @Autowired
    private PartialResultDAO partialResultDao;

    @Autowired
    private TotalResultDAO totalResultDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Segments getSegments(Long competitionId){
        Segments segments = new Segments();
        segmentDao.findByCompetitionId(competitionId).forEach(segment -> segments.add(segment.name));
        return segments;
    }

    public Optional<Competition> getCompetition(Long id){
        return competitionDao.findById(id);
    }

    public List<Competition> getAllCompetitions(){
        List<Competition> competitions = new ArrayList<Competition>();
        competitionDao.findAll().forEach(competitions::add);
        return competitions;
    }



    public void saveEventDataCollection(EventDataCollection eventData){
        Event mappedEvent = new EventFactory().getEvent(eventData);
        saveCompetition(mappedEvent.competition);
        saveSegments(mappedEvent.segments);
        saveCompetitors(mappedEvent.competitors);
        saveTotalResults(mappedEvent.totalResults);
        savePartialResults(mappedEvent.partialResults);
    }

    private void saveCompetition(Competition competition){
        competitionDao.save(competition);
    }

    private void saveSegments(List<Segment> segments){
        segmentDao.saveAll(segments);
    }

    private void saveCompetitors(List<Competitor> competitors){
        competitorDao.saveAll(competitors);
    }

    private void saveTotalResults(List<TotalResult> totalResults){
        totalResultDao.saveAll(totalResults);
    }

    private void savePartialResults(List<PartialResult> partialResults){
        partialResultDao.saveAll(partialResults);
    }
}
