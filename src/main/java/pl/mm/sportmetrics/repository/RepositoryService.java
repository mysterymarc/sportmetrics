package pl.mm.sportmetrics.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.repository.dao.*;
import pl.mm.sportmetrics.repository.entity.*;
import pl.mm.sportmetrics.model.repo.Segments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RepositoryService {

    @Autowired
    private CompetitionDAO competitionRepository;

    @Autowired
    private SegmentDAO segmentRepository;

    @Autowired
    private CompetitorDAO competitorRepository;

    @Autowired
    private PartialResultDAO partialResultRepository;

    @Autowired
    private TotalResultDAO totalResultRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Segments getSegments(Long competitionId){
        Segments segments = new Segments();
        segmentRepository.findByCompetitionId(competitionId).forEach(segment -> segments.add(segment.name));
        return segments;
    }

    public Optional<Competition> getCompetition(Long id){
        return competitionRepository.findById(id);
    }

    public List<Competition> getAllCompetitions(){
        List<Competition> competitions = new ArrayList<Competition>();
        competitionRepository.findAll().forEach(competitions::add);
        return competitions;
    }

    public void saveCompetition(Competition competition){
        competitionRepository.save(competition);
    }

    public void saveSegments(List<Segment> segments){

        segmentRepository.saveAll(segments);
    }

    public void saveCompetitors(List<Competitor> competitors){
        competitorRepository.saveAll(competitors);
    }

    public void saveTotalResults(List<TotalResult> totalResults){
        totalResultRepository.saveAll(totalResults);
    }

    public void savePartialResults(List<PartialResult> partialResults){
        partialResultRepository.saveAll(partialResults);
    }

}
