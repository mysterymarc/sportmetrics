package pl.mm.sportmetrics.model.viewobject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.mapper.RepoToViewOfResultsMatrixMapper;
import pl.mm.sportmetrics.repository.entity.Competition;
import pl.mm.sportmetrics.model.repo.ResultsForRunnersGroup;
import pl.mm.sportmetrics.model.repo.ResultsForRunnersGroupFactory;
import pl.mm.sportmetrics.model.repo.Segments;
import pl.mm.sportmetrics.repository.Repository;


@Service
public class ResultsService {

    @Autowired
    private Repository repository;

    @Autowired
    private ResultsForRunnersGroupFactory resultsForRunnersGroupFactory;

 public ResultsPageDTO getDataForView(Long competitionId){

     ResultsPageDTO results = new ResultsPageDTO();
     results.setCompetition(getCompetition(competitionId));
     results.setSegments(getSegments(competitionId));
     results.setResultRows(getResults(competitionId));

     return results;
 }

 private Competition getCompetition(Long competitionId){
     return repository.getCompetition(competitionId).orElseThrow(() ->
             new IllegalArgumentException("Repository doesn't return result for competition id=" + competitionId));
 }

 private Segments getSegments(Long competitionId){
     return repository.getSegments(competitionId);
 }

 private RowResultsGroupView getResults(Long competitionId){
     ResultsForRunnersGroup resultsForRunnersGroup = resultsForRunnersGroupFactory.getObject(competitionId);

     RowResultsGroupView detailResultRowForGroups;

     RepoToViewOfResultsMatrixMapper mapper = new RepoToViewOfResultsMatrixMapper();
     mapper.doMapping(resultsForRunnersGroup);
     detailResultRowForGroups = mapper.getResultsMatrix();

     return detailResultRowForGroups;
 }
}
