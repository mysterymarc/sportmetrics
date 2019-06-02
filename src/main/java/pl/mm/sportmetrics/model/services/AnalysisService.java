package pl.mm.sportmetrics.model.services;

import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.mapper.RepoToViewOfResultsMatrixMapper;
import pl.mm.sportmetrics.mapper.RepoToViewOfStatisticsMatrixMapper;
import pl.mm.sportmetrics.model.repo.*;
import pl.mm.sportmetrics.model.viewobject.AnalysisPageDTO;
import pl.mm.sportmetrics.model.viewobject.AnalysisResultsGroupsCollectionView;
import pl.mm.sportmetrics.model.viewobject.RowResultsGroupsColletionView;
import pl.mm.sportmetrics.repository.Repository;
import pl.mm.sportmetrics.statistics.Calculation;


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
