package pl.mm.sportmetrics.services;

import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.domain.logic.Average;
import pl.mm.sportmetrics.domain.logic.Calculation;
import pl.mm.sportmetrics.domain.model.*;
import pl.mm.sportmetrics.dto.viewlayer.*;
import pl.mm.sportmetrics.repository.CompetitionRepository;
import pl.mm.sportmetrics.repository.RunnersResultRepository;
import pl.mm.sportmetrics.repository.SegmentRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class AnalysisService {

    private RunnersResultRepository runnersResultRepository;

    private CompetitionRepository competitionRepository;

    private SegmentRepository segmentRepository;

    public AnalysisService(RunnersResultRepository runnersResultRepository, CompetitionRepository competitionRepository, SegmentRepository segmentRepository) {
        this.runnersResultRepository = runnersResultRepository;
        this.competitionRepository = competitionRepository;
        this.segmentRepository = segmentRepository;
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
                resultsForRunnersGroupsCollection.add(runnersResultRepository.findResultsByTotalResultIds(collection)));

        return resultsForRunnersGroupsCollection;
    }

    private Competition getCompetition(Long competitionId) {
        return competitionRepository.findCompetition(competitionId);
    }

    private Segments getSegments(Long competitionId) {
        return segmentRepository.findAllSegments(competitionId);
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
        SegmentsStatisticsForGroupsCollection segmentsStatisticsForGroupsCollection = new SegmentsStatisticsForGroupsCollection();

        List<Calculation> calculations = new ArrayList<>();
        calculations.add(new Average());
        //calculations.add(new Median());
        SegmentsStatisticsForGroupFactory segmentsStatisticsForGroupFactory = new SegmentsStatisticsForGroupFactory(calculations);

        for (ResultsForRunnersGroup group : resultsGroupsCollection) {
            segmentsStatisticsForGroupsCollection.add(segmentsStatisticsForGroupFactory.getObject(group));
        }

        segmentsStatisticsForGroupsCollection.evaluateStatisticsWithWinLossDescriptions(0,1);

        AnalysisResultsGroupsCollectionView detailAnalysisRowForGroups = new AnalysisResultsGroupsCollectionView();
        for (SegmentsStatisticsForGroup group : segmentsStatisticsForGroupsCollection) {
            detailAnalysisRowForGroups.add(mapBusinessResultsForGroupToViewResultsForGroup(group));
        }
        return detailAnalysisRowForGroups;
    }
    //TODO: przydaloby sie jakies uwspolnienie tego mapowania z tym dla resultow - wyglada identycznie
    private AnalysisResultsGroupView mapBusinessResultsForGroupToViewResultsForGroup(SegmentsStatisticsForGroup group){
        return new StatisticsMatrixFromBusinessToViewMapper().doMapping(group);
    }
}
