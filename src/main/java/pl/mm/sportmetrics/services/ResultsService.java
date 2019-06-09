package pl.mm.sportmetrics.services;

import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.domain.model.Competition;
import pl.mm.sportmetrics.domain.model.ResultsForRunnersGroup;
import pl.mm.sportmetrics.domain.model.Segments;
import pl.mm.sportmetrics.dto.viewlayer.ResultsMatrixFromBusinessToViewMapper;
import pl.mm.sportmetrics.dto.viewlayer.ResultsPageDTO;
import pl.mm.sportmetrics.dto.viewlayer.RowResultsGroupView;
import pl.mm.sportmetrics.repository.Repository;


@Service
public class ResultsService {

    private Repository repository;

    public ResultsService(Repository repository){
        this.repository = repository;
     }

    public ResultsPageDTO getDataForView(Long competitionId) {
        ResultsPageDTO results = new ResultsPageDTO();
        results.setCompetition(getCompetition(competitionId));
        results.setSegments(getSegments(competitionId));
        results.setResultRows(getResults(competitionId));
        return results;
    }

    private Competition getCompetition(Long competitionId) {
        return repository.getCompetition(competitionId);
    }

    private Segments getSegments(Long competitionId) {
        return repository.getSegments(competitionId);
    }

    private RowResultsGroupView getResults(Long competitionId) {
        ResultsForRunnersGroup resultsForRunnersGroup = repository.findResultsByCompetitionId(competitionId);
        return mapBusinessResultsForGroupToViewResultsForGroup(resultsForRunnersGroup);
    }

    private RowResultsGroupView mapBusinessResultsForGroupToViewResultsForGroup(ResultsForRunnersGroup resultsForRunnersGroup){
        return new ResultsMatrixFromBusinessToViewMapper().doMapping(resultsForRunnersGroup);
    }
}
