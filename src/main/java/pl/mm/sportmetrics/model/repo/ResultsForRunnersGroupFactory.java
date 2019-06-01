package pl.mm.sportmetrics.model.repo;

import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.repository.dao.PartialResultDAO;
import pl.mm.sportmetrics.repository.dao.TotalResultDAO;
import pl.mm.sportmetrics.repository.entity.PartialResult;
import pl.mm.sportmetrics.repository.entity.TotalResult;

import java.util.List;

@Service
public class ResultsForRunnersGroupFactory {

    private TotalResultDAO totalResultRepository;
    private PartialResultDAO partialResultRepository;

    public ResultsForRunnersGroupFactory(TotalResultDAO totalResultRepository,PartialResultDAO partialResultRepository){
        this.totalResultRepository = totalResultRepository;
        this.partialResultRepository = partialResultRepository;
    }

    public ResultsForRunnersGroup getObject(List<Long> totalResultIds) {

        ResultsForRunnersGroup resultsForRunnersGroup = new ResultsForRunnersGroup();

            for (Long totalResultId : totalResultIds) {
                TotalResult totalResult = totalResultRepository.findById(totalResultId).orElse(null);
                resultsForRunnersGroup.add(prepareScoreRow(totalResult));
            }

        return resultsForRunnersGroup;
    }

    public ResultsForRunnersGroup getObject(Long competitionId) {

        List<TotalResult> totalResults = totalResultRepository.findByCompetitionId(competitionId);

        ResultsForRunnersGroup resultsForRunnersGroup = new ResultsForRunnersGroup();

        for (TotalResult totalResult : totalResults){
            resultsForRunnersGroup.add(prepareScoreRow(totalResult));
        }

        return resultsForRunnersGroup;
    }


    private ResultsForRunner prepareScoreRow(TotalResult totalResult){
        ResultsForRunner resultsForRunner = new ResultsForRunner();
        resultsForRunner.totalResultId = totalResult.id;
        resultsForRunner.position = totalResult.position;
        resultsForRunner.totalTime = totalResult.totalTime;
        resultsForRunner.delayTime = totalResult.delayTime;
        resultsForRunner.competitorId = totalResult.id;
        resultsForRunner.competitorName = totalResult.competitor.name;
        resultsForRunner.competitorCity = totalResult.competitor.city;
        List<PartialResult> partialResults = partialResultRepository.findByTotalResultId(totalResult.id);
        for (PartialResult partialResult : partialResults){
            resultsForRunner.segmentResults.add(new Result(partialResult.segmentTime,partialResult.segmentPosition));
            resultsForRunner.cumulativeResults.add(new Result(partialResult.cumulativeTime,partialResult.cumulativePosition));
        }
        return resultsForRunner;
    }

}
