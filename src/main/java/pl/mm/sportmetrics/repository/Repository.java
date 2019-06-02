package pl.mm.sportmetrics.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.model.inputlayer.Event;
import pl.mm.sportmetrics.model.businesslayer.*;
import pl.mm.sportmetrics.repository.dao.*;
import pl.mm.sportmetrics.repository.entity.*;
import pl.mm.sportmetrics.repository.entity.EventDTO;
import pl.mm.sportmetrics.repository.mapper.EventDTOFactory;

import java.util.List;

@Service
public class Repository {

    private CompetitionDAO competitionDao;
    private SegmentDAO segmentDao;
    private CompetitorDAO competitorDao;
    private PartialResultDAO partialResultDao;
    private TotalResultDAO totalResultDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Repository(CompetitionDAO competitionDao, SegmentDAO segmentDao, CompetitorDAO competitorDao,
                      PartialResultDAO partialResultDao, TotalResultDAO totalResultDao) {
        this.competitionDao = competitionDao;
        this.segmentDao = segmentDao;
        this.competitorDao = competitorDao;
        this.partialResultDao = partialResultDao;
        this.totalResultDao = totalResultDao;
    }

    public Segments getSegments(Long competitionId){
        Segments segments = new Segments();
        segmentDao.findByCompetitionId(competitionId)
                .orElseThrow(()-> new IllegalArgumentException("Repository doesn't return Segments for competition_id=" + competitionId))
                .forEach(segment -> segments.add(segment.name));
        return segments;
    }

    public Competition getCompetition(Long id){
        return mapToModel(competitionDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Repository doesn't return CompetitionEntity for competition_id=" + id)));
    }

    public CompetitionsCollection findAllCompetitions(){
        CompetitionsCollection competitions = new CompetitionsCollection();
        competitionDao.findAll().forEach(entity -> competitions.add(mapToModel(entity)));
        return competitions;
    }

    public ResultsForRunnersGroup findResultsByTotalResultIds(IdentifiersOfResultsGroup totalResultIds){
        ResultsForRunnersGroup resultsForRunnersGroup = new ResultsForRunnersGroup();
        for(Long totalResultId : totalResultIds.getIdentifiers()){
            resultsForRunnersGroup.add(prepareScoreRow(findTotalResultById(totalResultId)));
        }
        return resultsForRunnersGroup;
    }

    public ResultsForRunnersGroup findResultsByCompetitionId(Long competitionId){
        List<TotalResultEntity> totalResults = findTotalResultsByCompetitionId(competitionId);
        ResultsForRunnersGroup resultsForRunnersGroup = new ResultsForRunnersGroup();
        for (TotalResultEntity totalResult : totalResults){
            resultsForRunnersGroup.add(prepareScoreRow(totalResult));
        }
        return resultsForRunnersGroup;
    }

    private TotalResultEntity findTotalResultById(Long totalResultId){
        return totalResultDao.findById(totalResultId).
                orElseThrow(()-> new IllegalArgumentException("Repository does't return TotalResultEntity for total_result_id=" + totalResultId));
    }

    private List<TotalResultEntity> findTotalResultsByCompetitionId(Long competitionId){
        return totalResultDao.findByCompetitionId(competitionId)
                .orElseThrow(() -> new IllegalArgumentException("Repository doesn't return TotalResultEntity for competition_id=" + competitionId));
    }

    private List<PartialResultEntity> findPartialResultsByTotalResultId(Long totalResultId){
        return partialResultDao.findByTotalResultId(totalResultId)
                .orElseThrow(() -> new IllegalArgumentException("Repository doesn't return PartialResultEntity for total_result_id=" + totalResultId));
    }
    //TODO: wylaczyc to do oddzielnych maperow
    private ResultsForRunner prepareScoreRow(TotalResultEntity totalResult){
        ResultsForRunner resultsForRunner = new ResultsForRunner();
        resultsForRunner.setTotalResultId(totalResult.id);
        resultsForRunner.setPosition(totalResult.position);
        resultsForRunner.setTotalTime(totalResult.totalTime);
        resultsForRunner.setDelayTime(totalResult.delayTime);
        resultsForRunner.setCompetitorId(totalResult.id);
        resultsForRunner.setCompetitorName(totalResult.competitor.name);
        resultsForRunner.setCompetitorCity(totalResult.competitor.city);
        List<PartialResultEntity> partialResults = findPartialResultsByTotalResultId(totalResult.id);
        for (PartialResultEntity partialResult : partialResults){
            resultsForRunner.addSegmentResult(new Result(partialResult.segmentTime,partialResult.segmentPosition));
            resultsForRunner.addCumulativeResult(new Result(partialResult.cumulativeTime,partialResult.cumulativePosition));
        }
        return resultsForRunner;
    }
//TODO: wylaczyc to do oddzielnych maperow
    private Competition mapToModel(CompetitionEntity entity){
        Competition competitionModel = new Competition();
        competitionModel.id = entity.id;
        competitionModel.name = entity.name;
        return competitionModel;
    }

    public void saveEvent(Event event){
        //TODO: Czy tu moze byc new, czy jednak na sile trzeba to tez springowi wciskac
        EventDTO mappedEvent = new EventDTOFactory().getEvent(event);
        saveCompetition(mappedEvent.competition);
        saveSegments(mappedEvent.segments);
        saveCompetitors(mappedEvent.competitors);
        saveTotalResults(mappedEvent.totalResults);
        savePartialResults(mappedEvent.partialResults);
    }

    private void saveCompetition(CompetitionEntity competition){
        competitionDao.save(competition);
    }

    private void saveSegments(List<SegmentEntity> segments){
        segmentDao.saveAll(segments);
    }

    private void saveCompetitors(List<CompetitorEntity> competitors){
        competitorDao.saveAll(competitors);
    }

    private void saveTotalResults(List<TotalResultEntity> totalResults){
        totalResultDao.saveAll(totalResults);
    }

    private void savePartialResults(List<PartialResultEntity> partialResults){
        partialResultDao.saveAll(partialResults);
    }
}
