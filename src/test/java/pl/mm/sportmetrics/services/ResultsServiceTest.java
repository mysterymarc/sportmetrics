package pl.mm.sportmetrics.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.mm.sportmetrics.domain.model.*;
import pl.mm.sportmetrics.dto.viewlayer.ResultsPageDTO;
import pl.mm.sportmetrics.dto.viewlayer.RowResultView;
import pl.mm.sportmetrics.dto.viewlayer.RowResultsGroupView;
import pl.mm.sportmetrics.dto.viewlayer.SingleResultView;
import pl.mm.sportmetrics.repository.CompetitionRepository;
import pl.mm.sportmetrics.repository.RunnersResultRepository;
import pl.mm.sportmetrics.repository.SegmentRepository;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultsServiceTest {
    @Mock
    private RunnersResultRepository runnersResultRepository;

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private SegmentRepository segmentRepository;

    @Test
    public void givenCompetitionIdWhenRequestToResultsServiceThenDTOWithResultsReturned() {
        Long givenCompetitionId = 1L;
        givenCompetition(givenCompetitionId);
        givenSegments(givenCompetitionId);
        givenResults(givenCompetitionId);

        ResultsPageDTO results = new ResultsService(runnersResultRepository,competitionRepository, segmentRepository).getDataForView(givenCompetitionId);

        assertThat(results).isEqualTo(expectedResultPageDTO(givenCompetitionId));
    }

    private void givenSegments(Long competitionId) {
        when(segmentRepository.findAllSegments(competitionId)).thenReturn(getSegments());
    }

    private Segments getSegments(){
        return new Segments(Arrays.asList("1-2","3-5","5-6"));
    }

    private void givenCompetition(Long competitionId) {
        when(competitionRepository.findCompetition(competitionId)).thenReturn(getCompetition(competitionId));
    }

    private Competition getCompetition(Long competitionId){
        return new Competition(competitionId,"Example competition");
    }

    private void givenResults(Long competitionId){
        ResultsForRunnersGroup resultsForRunnersGroup = new ResultsForRunnersGroup();
        resultsForRunnersGroup.add(getResultsForRunner());
        resultsForRunnersGroup.add(getResultsForRunner());
        when(runnersResultRepository.findResultsByCompetitionId(competitionId)).thenReturn(resultsForRunnersGroup);
    }

    private ResultsForRunner getResultsForRunner(){
        return new ResultsForRunner.Builder()
                .withName("Janusz Nosacz")
                .fromCity("Wrocław")
                .achievedPosition(2)
                .reachedTotalTime("00:00:20")
                .withDelayToWinner("00:00:30")
                .withSegmentResults(Arrays.asList(new Result("00:00:20", 1)))
                .withCumulativeResults(Arrays.asList(new Result("00:00:40", 2)))
                .competitorSignedById(25L)
                .totalResultSignedById(3L)
                .build();
    }

    private ResultsPageDTO expectedResultPageDTO(Long competitionId){
        ResultsPageDTO resultsPageDTO = new ResultsPageDTO();
        resultsPageDTO.setCompetition(getCompetition(competitionId));
        resultsPageDTO.setSegments(getSegments());
        resultsPageDTO.setResultRows(getResultRows());
        return resultsPageDTO;
    }

    private RowResultsGroupView getResultRows(){
        RowResultsGroupView rowResultsGroupView = new RowResultsGroupView();
        rowResultsGroupView.add(getSingleRowResult());
        rowResultsGroupView.add(getSingleRowResult());
        return rowResultsGroupView;
    }

    private RowResultView getSingleRowResult() {
        return new RowResultView(25L, 3L, "Janusz Nosacz", "Wrocław", "2", "00:20", "+00:30",
                Arrays.asList(getSingleResult("00:20","1")),Arrays.asList(getSingleResult("00:40","2")));
    }

    private SingleResultView getSingleResult(String time, String position) {
        return new SingleResultView(time, position);
    }
}