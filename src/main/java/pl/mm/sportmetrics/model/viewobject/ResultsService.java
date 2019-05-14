package pl.mm.sportmetrics.model.viewobject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.mm.sportmetrics.mapper.RepoToViewOfResultsMatrixMapper;
import pl.mm.sportmetrics.model.database.Competition;
import pl.mm.sportmetrics.model.database.Segment;
import pl.mm.sportmetrics.model.repo.ResultsForRunnersGroup;
import pl.mm.sportmetrics.model.repo.ResultsForRunnersGroupFactory;
import pl.mm.sportmetrics.repository.RepositoryService;

import java.util.List;
import java.util.Optional;


@Service
public class ResultsService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ResultsForRunnersGroupFactory resultsForRunnersGroupFactory;

 public ResultsPageDataView getDataForView(Long competitionId){

     ResultsPageDataView results = new ResultsPageDataView();

     Optional<Competition> competition = repositoryService.getCompetition(competitionId);
     results.setCompetition(competition.orElseThrow(() ->
             new IllegalArgumentException("Repository doesn't return result for competition id=" + competitionId)));

     List<Segment> segments = repositoryService.getSegments(Long.valueOf(competitionId));
     results.setSegments(segments);

     ResultsForRunnersGroup resultsForRunnersGroup = resultsForRunnersGroupFactory.getObject(competitionId);

     RowResultsGroupView detailResultRowForGroups;

     RepoToViewOfResultsMatrixMapper mapper = new RepoToViewOfResultsMatrixMapper();
     mapper.doMapping(resultsForRunnersGroup);
     detailResultRowForGroups = mapper.getResultsMatrix();

     results.setResultRows(detailResultRowForGroups);

     return results;
 }

}
