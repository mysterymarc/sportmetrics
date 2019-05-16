package pl.mm.sportmetrics.model.viewobject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.mm.sportmetrics.mapper.RepoToViewOfResultsMatrixMapper;
import pl.mm.sportmetrics.mapper.RepoToViewOfStatisticsMatrixMapper;
import pl.mm.sportmetrics.model.database.Competition;
import pl.mm.sportmetrics.model.database.Segment;
import pl.mm.sportmetrics.model.repo.*;
import pl.mm.sportmetrics.repository.RepositoryService;
import pl.mm.sportmetrics.statistics.Calculation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnalysisService {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ResultsForRunnersGroupFactory resultsForRunnersGroupFactory;

    public AnalysisPageDataView getDataForView(Long competitionId, List<String> firstGroup, List<String> secondGroup) {

        AnalysisPageDataView viewData = new AnalysisPageDataView();

        Optional<Competition> competition = repositoryService.getCompetition(competitionId);
        viewData.setCompetition(competition.orElseThrow(() ->
                new IllegalArgumentException("Repository doesn't return result for competition id=" + competitionId)));

        Segments segments = repositoryService.getSegments(Long.valueOf(competitionId));
        viewData.setSegments(segments);


        List<List<String>> resultIdsForGroups = new ArrayList<>();
        resultIdsForGroups.add(firstGroup);
        resultIdsForGroups.add(secondGroup);


        // TODO: model + coś co ten model buduje i zwraca (zwykle to by się nazywało Repository... ale z tym jest zamota
        // bo już masz Repository służące za DAO)
        // -> https://stackoverflow.com/questions/8550124/what-is-the-difference-between-dao-and-repository-patterns
        // -> https://blog.sapiensworks.com/post/2012/11/01/Repository-vs-DAO.aspx

        ResultsForRunnersGroupsCollection resultsForRunnersGroupsCollection = new ResultsForRunnersGroupsCollection();

        for (List<String> resultIds : resultIdsForGroups) {
            resultsForRunnersGroupsCollection.add(resultsForRunnersGroupFactory.getObject(resultIds));
        }


        RowResultsGroupsColletionView detailResultRowForGroups = new RowResultsGroupsColletionView();

        for (ResultsForRunnersGroup group : resultsForRunnersGroupsCollection) {
            RepoToViewOfResultsMatrixMapper mapper = new RepoToViewOfResultsMatrixMapper();
            mapper.doMapping(group);
            detailResultRowForGroups.add(mapper.getResultsMatrix());
        }


        viewData.setResults(detailResultRowForGroups);

        SegmentsStatisticsGroupsCollection segmentsStatisticsGroupsCollection = new SegmentsStatisticsGroupsCollection();
        SegmentsStatisticsGroupFactory segmentsStatisticsGroupFactory = new SegmentsStatisticsGroupFactory();


        for (ResultsForRunnersGroup group : resultsForRunnersGroupsCollection) {
            segmentsStatisticsGroupsCollection.add(segmentsStatisticsGroupFactory.getObject(group));
        }

        new Calculation().setWinLossAttributeToStatisticsByItsComparison(
                segmentsStatisticsGroupsCollection.getGroup(0).getRow(0),
                segmentsStatisticsGroupsCollection.getGroup(1).getRow(0));


        List<List<AnalysisResultRow>> detailAnalysisRowForGroups = new ArrayList<>();

        for (SegmentsStatisticsGroup group : segmentsStatisticsGroupsCollection) {
            RepoToViewOfStatisticsMatrixMapper mapper = new RepoToViewOfStatisticsMatrixMapper();
            mapper.doMapping(group);
            detailAnalysisRowForGroups.add(mapper.getResultsMatrix());
        }

        viewData.setAvgAnalysis(detailAnalysisRowForGroups);

        return viewData;
    }

}
