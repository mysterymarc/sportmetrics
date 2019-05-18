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


@Service
public class AnalysisService {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ResultsForRunnersGroupFactory resultsForRunnersGroupFactory;

    public AnalysisPageDataView getDataForView(Long competitionId, IdentifiersOfResultsGroupsCollection identifiersGroupsCollection) {

        ResultsForRunnersGroupsCollection resultsForRunnersGroupsCollection =
                getResultsForSpecifiedGroupFromModel(identifiersGroupsCollection);

        AnalysisPageDataView viewData = new AnalysisPageDataView();
        viewData.setCompetition(getCompetition(competitionId));
        viewData.setSegments(getSegments(competitionId));
        viewData.setResults(getResults(resultsForRunnersGroupsCollection));
        viewData.setAvgAnalysis(getAnalyses(resultsForRunnersGroupsCollection));

        return viewData;
    }

    private ResultsForRunnersGroupsCollection getResultsForSpecifiedGroupFromModel(IdentifiersOfResultsGroupsCollection identifiersGroupsCollection) {

        ResultsForRunnersGroupsCollection resultsForRunnersGroupsCollection = new ResultsForRunnersGroupsCollection();

        identifiersGroupsCollection.forEach(collection ->
                resultsForRunnersGroupsCollection.add(resultsForRunnersGroupFactory.getObject(collection.getIdentifiers())));

        return resultsForRunnersGroupsCollection;
    }

    private Competition getCompetition(Long competitionId) {
        return repositoryService.getCompetition(competitionId).orElseThrow(() ->
                new IllegalArgumentException("Repository doesn't return result for competition id=" + competitionId));
    }

    private Segments getSegments(Long competitionId) {
        return repositoryService.getSegments(competitionId);
    }

    private RowResultsGroupsColletionView getResults(ResultsForRunnersGroupsCollection resultsGroupsCollection) {

        // TODO: model + coś co ten model buduje i zwraca (zwykle to by się nazywało Repository... ale z tym jest zamota
        // bo już masz Repository służące za DAO)
        // -> https://stackoverflow.com/questions/8550124/what-is-the-difference-between-dao-and-repository-patterns
        // -> https://blog.sapiensworks.com/post/2012/11/01/Repository-vs-DAO.aspx


        RowResultsGroupsColletionView detailResultRowForGroups = new RowResultsGroupsColletionView();

        for (ResultsForRunnersGroup group : resultsGroupsCollection) {
            RepoToViewOfResultsMatrixMapper mapper = new RepoToViewOfResultsMatrixMapper();
            mapper.doMapping(group);
            detailResultRowForGroups.add(mapper.getResultsMatrix());
        }

        return detailResultRowForGroups;
    }

    private AnalysisResultsGroupsCollectionView getAnalyses(ResultsForRunnersGroupsCollection resultsGroupsCollection) {
        SegmentsStatisticsGroupsCollection segmentsStatisticsGroupsCollection = new SegmentsStatisticsGroupsCollection();
        SegmentsStatisticsGroupFactory segmentsStatisticsGroupFactory = new SegmentsStatisticsGroupFactory();

        for (ResultsForRunnersGroup group : resultsGroupsCollection) {
            segmentsStatisticsGroupsCollection.add(segmentsStatisticsGroupFactory.getObject(group));
        }

        new Calculation().setWinLossAttributeToStatisticsByItsComparison(
                segmentsStatisticsGroupsCollection.getGroup(0).getRow(0),
                segmentsStatisticsGroupsCollection.getGroup(1).getRow(0));

        AnalysisResultsGroupsCollectionView detailAnalysisRowForGroups = new AnalysisResultsGroupsCollectionView();

        for (SegmentsStatisticsGroup group : segmentsStatisticsGroupsCollection) {
            RepoToViewOfStatisticsMatrixMapper mapper = new RepoToViewOfStatisticsMatrixMapper();
            mapper.doMapping(group);
            detailAnalysisRowForGroups.add(mapper.getResultsMatrix());
        }

        return detailAnalysisRowForGroups;
    }
}
