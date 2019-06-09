package pl.mm.sportmetrics.dto.viewlayer;

import org.junit.Test;
import pl.mm.sportmetrics.domain.model.SegmentsStatistic;
import pl.mm.sportmetrics.domain.model.SegmentsStatisticsForGroup;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsMatrixFromBusinessToViewMapperTest {

    @Test
    public void givenStatisticsBusinessModelWhenMappingToViewThenCorrectViewModelReturned() {
        //given
        SegmentsStatisticsForGroup group = givenStatisticsForGroup();
        //when
        AnalysisResultsGroupView analyseView = new StatisticsMatrixFromBusinessToViewMapper().doMapping(group);
        //then
        assertThat(analyseView.getAnalyses()).containsExactly(new AnalysisResultRow(
                "Example title",
                Arrays.asList(
                        new AnalysisResultForSegment("00:03", "win"),
                        new AnalysisResultForSegment("01:00:00","loss"),
                        new AnalysisResultForSegment("","draw")
                )));
    }

    private SegmentsStatisticsForGroup givenStatisticsForGroup(){
        SegmentsStatisticsForGroup group = new SegmentsStatisticsForGroup();
        SegmentsStatistic row = new SegmentsStatistic();
        row.addStatistic("00:00:03", "win");
        row.addStatistic("01:00:00", "loss");
        row.addStatistic("00:00:00", "draw");
        row.setTitle("Example title");
        group.add(row);

        return group;
    }

}
