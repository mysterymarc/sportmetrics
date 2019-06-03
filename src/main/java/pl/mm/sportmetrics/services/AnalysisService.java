package pl.mm.sportmetrics.services;

import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.model.businesslayer.*;
import pl.mm.sportmetrics.model.viewlayer.*;
import pl.mm.sportmetrics.repository.Repository;


@Service
public class AnalysisService {

    private Repository repository;

    public AnalysisService(Repository repository){
        this.repository = repository;
    }

    public AnalysisPageDTO getDataForView(Long competitionId, IdentifiersOfResultsGroupsCollection identifiersGroupsCollection) {

        ResultsForRunnersGroupsCollection resultsForRunnersGroupsCollection =
                getResultsForSpecifiedGroupFromModel(identifiersGroupsCollection);

        AnalysisPageDTO viewData = new AnalysisPageDTO();
        viewData.setCompetition(getCompetition(competitionId));
        viewData.setSegments(getSegments(competitionId));
        viewData.setResults(getResults(resultsForRunnersGroupsCollection));
        viewData.setAnalyses(getAnalyses(resultsForRunnersGroupsCollection));

        return viewData;
    }

    private ResultsForRunnersGroupsCollection getResultsForSpecifiedGroupFromModel(IdentifiersOfResultsGroupsCollection identifiersGroupsCollection) {

        ResultsForRunnersGroupsCollection resultsForRunnersGroupsCollection = new ResultsForRunnersGroupsCollection();

        identifiersGroupsCollection.forEach(collection ->
                resultsForRunnersGroupsCollection.add(repository.findResultsByTotalResultIds(collection)));

        return resultsForRunnersGroupsCollection;
    }

    private Competition getCompetition(Long competitionId) {
        return repository.getCompetition(competitionId);
    }

    private Segments getSegments(Long competitionId) {
        return repository.getSegments(competitionId);
    }

    private RowResultsGroupsColletionView getResults(ResultsForRunnersGroupsCollection resultsGroupsCollection) {
        RowResultsGroupsColletionView detailResultRowForGroups = new RowResultsGroupsColletionView();
        for (ResultsForRunnersGroup group : resultsGroupsCollection) {
            detailResultRowForGroups.add(mapBusinessResultsForGroupToViewResultsForGroup(group));
        }
        return detailResultRowForGroups;
    }

    private RowResultsGroupView mapBusinessResultsForGroupToViewResultsForGroup(ResultsForRunnersGroup group){
        return new ResultsMatrixFromBusinessToViewMapper().doMapping(group);
    }

    private AnalysisResultsGroupsCollectionView getAnalyses(ResultsForRunnersGroupsCollection resultsGroupsCollection) {
        SegmentsStatisticsGroupsCollection segmentsStatisticsGroupsCollection = new SegmentsStatisticsGroupsCollection();
        SegmentsStatisticsGroupFactory segmentsStatisticsGroupFactory = new SegmentsStatisticsGroupFactory();

        for (ResultsForRunnersGroup group : resultsGroupsCollection) {
            segmentsStatisticsGroupsCollection.add(segmentsStatisticsGroupFactory.getObject(group));
        }

        segmentsStatisticsGroupsCollection.evaluateStatisticsWithWinLossDescriptions(0,1);

        AnalysisResultsGroupsCollectionView detailAnalysisRowForGroups = new AnalysisResultsGroupsCollectionView();
        for (SegmentsStatisticsGroup group : segmentsStatisticsGroupsCollection) {
            detailAnalysisRowForGroups.add(mapBusinessResultsForGroupToViewResultsForGroup(group));
        }
        return detailAnalysisRowForGroups;
    }
    //TODO: przydaloby sie jakies uwspolnienie tego mapowania z tym dla resultow - wyglada identycznie
    private AnalysisResultsGroupView mapBusinessResultsForGroupToViewResultsForGroup(SegmentsStatisticsGroup group){
        return new StatisticsMatrixFromBusinessToViewMapper().doMapping(group);
    }
}
