package pl.mm.sportmetrics.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.mm.sportmetrics.domain.model.*;
import pl.mm.sportmetrics.dto.viewlayer.*;
import pl.mm.sportmetrics.repository.CompetitionRepository;
import pl.mm.sportmetrics.repository.RunnersResultRepository;
import pl.mm.sportmetrics.repository.SegmentRepository;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnalysisServiceTest {

    @Mock
    private RunnersResultRepository runnersResultRepository;

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private SegmentRepository segmentRepository;

    private AnalysisService analysisService;

    @Before
    public void setup() {
        analysisService = new AnalysisService(runnersResultRepository, competitionRepository, segmentRepository);
    }

    @Test
    public void givenCompetitionIdAndResultIdWhenCallToServiceToReturnDataForViewThenCorrectDataAreReturned() {
        //given
        IdentifiersOfResultsGroupsCollection groups = givenCorrectGroupCollection();
        long competitionId = 1L;
        String competitionName = "Example competition name";
        String segmentName = "S-1";
        givenRepositoryReturnsCompetition(competitionId, competitionName);
        givenRepositoryReturnsSegments(competitionId, segmentName);
        givenRepositoryReturnsResultsForRunnersGroups(groups);
        //when
        AnalysisPageDTO viewData = analysisService.getDataForView(competitionId, groups);
        //then
        assertThat(viewData).isEqualTo(new AnalysisPageDTO(
                new Competition(competitionId, competitionName),
                new Segments(Arrays.asList(segmentName)),
                expectedRowResultsGroupsCollectionView(),
                expectedAnalysisResultsGroupsCollectionView()
        ));
    }

    private IdentifiersOfResultsGroupsCollection givenCorrectGroupCollection() {
        IdentifiersOfResultsGroup resultsIdsGroup = new IdentifiersOfResultsGroup();
        resultsIdsGroup.add(3L);
        IdentifiersOfResultsGroupsCollection groupsCollection = new IdentifiersOfResultsGroupsCollection();
        groupsCollection.add(resultsIdsGroup);
        groupsCollection.add(resultsIdsGroup);      //two groups need to be created (consist the same id in this case)
        return groupsCollection;
    }

    private void givenRepositoryReturnsResultsForRunnersGroups(IdentifiersOfResultsGroupsCollection groupIdsCollection) {
        IdentifiersOfResultsGroup groupId = groupIdsCollection.iterator().next();
        when(runnersResultRepository.findResultsByTotalResultIds(groupId)).thenReturn(givenResultsForRunnersGroup());
    }

    private void givenRepositoryReturnsCompetition(Long competitionId, String competitionName) {
        when(competitionRepository.findCompetition(competitionId)).thenReturn(new Competition(competitionId, competitionName));
    }

    private void givenRepositoryReturnsSegments(Long competitionId, String segmentName) {
        when(segmentRepository.findAllSegments(competitionId)).thenReturn(new Segments(Arrays.asList(segmentName)));
    }

    private ResultsForRunnersGroup givenResultsForRunnersGroup() {
        ResultsForRunnersGroup group = new ResultsForRunnersGroup();
        group.add(new ResultsForRunner.Builder()
            .withName("Janusz Nosacz")
            .fromCity("Wrocław")
                .achievedPosition(2)
                .reachedTotalTime("00:00:20")
                .withDelayToWinner("00:00:30")
                .withSegmentResults(Arrays.asList(new Result("00:00:20", 1)))
                .withCumulativeResults(Arrays.asList(new Result("00:00:40", 2)))
                .competitorSignedById(25L)
                .totalResultSignedById(3L)
                .build());
        return group;
    }

    private RowResultView expectedRowResultView() {
        return new RowResultView(
                25L,
                3L,
                "Janusz Nosacz",
                "Wrocław",
                "2",
                "00:20",
                "+00:30",
                Arrays.asList(new SingleResultView("00:20", "1")),
                Arrays.asList(new SingleResultView("00:40", "2"))
        );
    }

    private RowResultsGroupsColletionView expectedRowResultsGroupsCollectionView() {
        return new RowResultsGroupsColletionView(
                Arrays.asList(new RowResultsGroupView(
                                Arrays.asList(expectedRowResultView())),
                        new RowResultsGroupView( //because two groups were created
                                Arrays.asList(expectedRowResultView()))
                )
        );
    }

    private AnalysisResultsGroupsCollectionView expectedAnalysisResultsGroupsCollectionView(){
        return new AnalysisResultsGroupsCollectionView(
                Arrays.asList(new AnalysisResultsGroupView(
                                Arrays.asList(expectedAverageAnalysisResultRow(), expectedMedianAnalysisResultRow())),
                        new AnalysisResultsGroupView( //because two groups were created
                                Arrays.asList(expectedAverageAnalysisResultRow(), expectedMedianAnalysisResultRow())
                        ))
        );
    }

    private AnalysisResultRow expectedAverageAnalysisResultRow(){
        return new AnalysisResultRow(
                "Average Time",
                Arrays.asList(new AnalysisResultForSegment("00:20", "draw"))
        );
    }

    private AnalysisResultRow expectedMedianAnalysisResultRow(){
        return new AnalysisResultRow(
                "Median Time",
                Arrays.asList(new AnalysisResultForSegment("00:20", "draw"))
        );
    }
}
