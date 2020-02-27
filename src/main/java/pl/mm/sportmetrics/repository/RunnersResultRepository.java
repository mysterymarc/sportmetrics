package pl.mm.sportmetrics.repository;

import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.domain.model.IdentifiersOfResultsGroup;
import pl.mm.sportmetrics.domain.model.Result;
import pl.mm.sportmetrics.domain.model.ResultsForRunner;
import pl.mm.sportmetrics.domain.model.ResultsForRunnersGroup;
import pl.mm.sportmetrics.repository.dao.CompetitorDAO;
import pl.mm.sportmetrics.repository.dao.PartialResultDAO;
import pl.mm.sportmetrics.repository.dao.TotalResultDAO;
import pl.mm.sportmetrics.repository.entity.CompetitorEntity;
import pl.mm.sportmetrics.repository.entity.EventEntitiesSet;
import pl.mm.sportmetrics.repository.entity.PartialResultEntity;
import pl.mm.sportmetrics.repository.entity.TotalResultEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class RunnersResultRepository {

    private final CompetitorDAO competitorDao;
    private final PartialResultDAO partialResultDao;
    private final TotalResultDAO totalResultDao;

    public RunnersResultRepository(CompetitorDAO competitorDao, PartialResultDAO partialResultDao, TotalResultDAO totalResultDao) {
        this.competitorDao = competitorDao;
        this.partialResultDao = partialResultDao;
        this.totalResultDao = totalResultDao;
    }

    public ResultsForRunnersGroup findResultsByCompetitionId(Long competitionId){
        List<TotalResultEntity> totalResults = findTotalResultsByCompetitionId(competitionId);
        ResultsForRunnersGroup resultsForRunnersGroup = new ResultsForRunnersGroup();
        for (TotalResultEntity totalResult : totalResults){
            resultsForRunnersGroup.add(mapEntityToBusinessModel(totalResult));
        }
        return resultsForRunnersGroup;
    }

    public ResultsForRunnersGroup findResultsByTotalResultIds(IdentifiersOfResultsGroup totalResultIds){
        ResultsForRunnersGroup resultsForRunnersGroup = new ResultsForRunnersGroup();
        for(Long totalResultId : totalResultIds.getIdentifiers()){
            resultsForRunnersGroup.add(mapEntityToBusinessModel(findTotalResultById(totalResultId)));
        }
        return resultsForRunnersGroup;
    }

    private List<TotalResultEntity> findTotalResultsByCompetitionId(Long competitionId){
        return totalResultDao.findByCompetitionId(competitionId)
                .orElseThrow(() -> new IllegalArgumentException("Repository doesn't return TotalResultEntity for competition_id=" + competitionId));
    }

    private TotalResultEntity findTotalResultById(Long totalResultId){
        return totalResultDao.findById(totalResultId).
                orElseThrow(()-> new IllegalArgumentException("Repository does't return TotalResultEntity for total_result_id=" + totalResultId));
    }

    private List<PartialResultEntity> findPartialResultsByTotalResultId(Long totalResultId){
        return partialResultDao.findByTotalResultId(totalResultId)
                .orElseThrow(() -> new IllegalArgumentException("Repository doesn't return PartialResultEntity for total_result_id=" + totalResultId));
    }

    private ResultsForRunner mapEntityToBusinessModel(TotalResultEntity totalResult){
        return new ResultsForRunner.Builder()
                .withName(totalResult.competitor.name)
                .fromCity(totalResult.competitor.city)
                .achievedPosition(totalResult.position)
                .reachedTotalTime(totalResult.totalTime)
                .withDelayToWinner(totalResult.delayTime)
                .withSegmentResults(findSegmentResultsByTotalResultId(totalResult.id))
                .withCumulativeResults(findCumulativeResultsByTotalResultId(totalResult.id))
                .competitorSignedById(totalResult.competitor.id)
                .totalResultSignedById(totalResult.id)
                .build();
    }

    private List<Result> findSegmentResultsByTotalResultId(Long totalResultId){
        List<Result> results = new ArrayList<>();
        List<PartialResultEntity> partialResults = findPartialResultsByTotalResultId(totalResultId);
        for (PartialResultEntity partialResult : partialResults){
            results.add(new Result(partialResult.segmentTime,partialResult.segmentPosition));
        }
        return results;
    }

    private List<Result> findCumulativeResultsByTotalResultId(Long totalResultId){
        List<Result> results = new ArrayList<>();
        List<PartialResultEntity> partialResults = findPartialResultsByTotalResultId(totalResultId);
        for (PartialResultEntity partialResult : partialResults){
            results.add(new Result(partialResult.cumulativeTime,partialResult.cumulativePosition));
        }
        return results;
    }

    public void saveRunnersResult(EventEntitiesSet event){
        saveCompetitors(event.competitors);
        saveTotalResults(event.totalResults);
        savePartialResults(event.partialResults);
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
