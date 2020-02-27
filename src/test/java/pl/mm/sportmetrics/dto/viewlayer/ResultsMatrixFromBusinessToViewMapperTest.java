package pl.mm.sportmetrics.dto.viewlayer;

import org.junit.Test;
import pl.mm.sportmetrics.domain.model.Result;
import pl.mm.sportmetrics.domain.model.ResultsForRunner;
import pl.mm.sportmetrics.domain.model.ResultsForRunnersGroup;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultsMatrixFromBusinessToViewMapperTest {

    @Test
    public void givenResultsBusinessModelWhenMappingToViewThenCorrectViewModelReturned() {
        //given
        ResultsForRunnersGroup group = givenResultsForRunnersGroup();
        //when
        RowResultsGroupView result = new ResultsMatrixFromBusinessToViewMapper().doMapping(group);
        //then
        assertThat(result.getRowsView()).containsExactly(
                new RowResultView(
                        25L,
                        34L,
                        "Janusz Nosacz",
                        "Wrocław",
                        "2",
                        "01:20",
                        "+00:40",
                        Arrays.asList(new SingleResultView("00:20", "1"), new SingleResultView("01:00", "2")),
                        Arrays.asList(new SingleResultView("00:20", "2"), new SingleResultView("01:20", "2"))),
                new RowResultView(
                        28L,
                        36L,
                        "Grażyna Sundajska",
                        "Mongolia",
                        "x",
                        "",
                        "",
                        Arrays.asList(new SingleResultView("00:40", "3"), new SingleResultView("", "x")),
                        Arrays.asList(new SingleResultView("00:40", "3"), new SingleResultView("", "x"))
                )
        );
    }

    private ResultsForRunnersGroup givenResultsForRunnersGroup() {
        ResultsForRunnersGroup group = new ResultsForRunnersGroup();
        group.add(new ResultsForRunner.Builder()
                .withName("Janusz Nosacz")
                .fromCity("Wrocław")
                .achievedPosition(2)
                .reachedTotalTime("00:01:20")
                .withDelayToWinner("00:00:40")
                .withSegmentResults(Arrays.asList(new Result("00:00:20", 1), new Result("00:01:00", 2)))
                .withCumulativeResults(Arrays.asList(new Result("00:00:20", 2), new Result("00:01:20", 2)))
                .competitorSignedById(25L)
                .totalResultSignedById(34L)
                .build());

        group.add(new ResultsForRunner.Builder()
                .withName("Grażyna Sundajska")
                .fromCity("Mongolia")
                .achievedPosition(-1)
                .reachedTotalTime("00:00:00")
                .withDelayToWinner("00:00:00")
                .withSegmentResults(Arrays.asList(new Result("00:00:40", 3), new Result("00:00:00", -1)))
                .withCumulativeResults(Arrays.asList(new Result("00:00:40", 3), new Result("00:00:00", -1)))
                .competitorSignedById(28L)
                .totalResultSignedById(36L)
                .build());

        return group;
    }
}
