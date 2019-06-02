package pl.mm.sportmetrics.model.services;

import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.mapper.RepoToViewOfResultsMatrixMapper;
import pl.mm.sportmetrics.model.repo.Competition;
import pl.mm.sportmetrics.model.repo.ResultsForRunnersGroup;
import pl.mm.sportmetrics.model.repo.Segments;
import pl.mm.sportmetrics.model.viewobject.ResultsPageDTO;
import pl.mm.sportmetrics.model.viewobject.RowResultsGroupView;
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

        RowResultsGroupView detailResultRowForGroups;

        RepoToViewOfResultsMatrixMapper mapper = new RepoToViewOfResultsMatrixMapper();
        mapper.doMapping(resultsForRunnersGroup);
        detailResultRowForGroups = mapper.getResultsMatrix();

        return detailResultRowForGroups;
    }
}
