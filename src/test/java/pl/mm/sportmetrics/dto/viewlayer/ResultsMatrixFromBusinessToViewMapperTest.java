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
        group.add(new ResultsForRunner(
                2,
                "Janusz Nosacz",
                "Wrocław",
                "00:01:20",
                "00:00:40",
                Arrays.asList(new Result("00:00:20", 1), new Result("00:01:00", 2)),
                Arrays.asList(new Result("00:00:20", 2), new Result("00:01:20", 2)),
                25L,
                34L
        ));

        group.add(new ResultsForRunner(
                -1,
                "Grażyna Sundajska",
                "Mongolia",
                "00:00:00",
                "00:00:00",
                Arrays.asList(new Result("00:00:40", 3), new Result("00:00:00", -1)),
                Arrays.asList(new Result("00:00:40", 3), new Result("00:00:00", -1)),
                28L,
                36L
        ));

        return group;
    }
}
