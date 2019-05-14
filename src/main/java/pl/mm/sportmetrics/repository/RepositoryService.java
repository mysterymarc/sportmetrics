package pl.mm.sportmetrics.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.mm.sportmetrics.model.database.*;
import pl.mm.sportmetrics.model.repo.ResultsForRunnersGroupFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RepositoryService {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private CompetitorRepository competitorRepository;

    @Autowired
    private PartialResultRepository partialResultRepository;

    @Autowired
    private TotalResultRepository totalResultRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Segment> getSegments(Long competitionId){
        return segmentRepository.findByCompetitionId(competitionId);
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
