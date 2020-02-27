package pl.mm.sportmetrics.domain.model;

import org.junit.Test;
import pl.mm.sportmetrics.domain.logic.Average;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SegmentsStatisticsForGroupFactoryTest {

    ResultsForRunnersGroup resultsForRunnersGroup;
    SegmentsStatisticsForGroupFactory factory;

    @Test
    public void givenResultsForRunnersGroupWhenGetObjectFromFactoryThenSegmentsStatisticsForGroupReturned() {
        givenResultsForRunnersGroup();
        givenFactoryForAverageStatistic();

        SegmentsStatisticsForGroup statistics = factory.getObject(resultsForRunnersGroup);

        assertThat(statistics).isEqualTo(getExpectedStatisticForGroup());
    }

    private void givenResultsForRunnersGroup() {
        resultsForRunnersGroup = new ResultsForRunnersGroup();
        resultsForRunnersGroup.add(new ResultsForRunner.Builder()
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

        resultsForRunnersGroup.add(new ResultsForRunner.Builder()
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
    }

    private void givenFactoryForAverageStatistic() {
        factory = new SegmentsStatisticsForGroupFactory(Arrays.asList(new Average()));
    }

    private SegmentsStatisticsForGroup getExpectedStatisticForGroup() {
        SegmentsStatistic segmentsStatistic = new SegmentsStatistic();
        segmentsStatistic.setTitle("Average Time");
        segmentsStatistic.addStatistic("00:00:30");
        segmentsStatistic.addStatistic("00:00:00");
        SegmentsStatisticsForGroup groupStatistics = new SegmentsStatisticsForGroup();
        groupStatistics.add(segmentsStatistic);
        return groupStatistics;
    }

}