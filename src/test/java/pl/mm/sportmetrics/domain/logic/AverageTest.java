package pl.mm.sportmetrics.domain.logic;

import org.junit.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AverageTest {

    @Test
    public void givenTimeMatrixWhenAverageAppliedOnMatrixThenCalculatedCorrectAvgByColumns() {
        //given
        TimeMatrix times = givenTimeMatrix();
        //when
        TimeList statistic = new Average().getStatistic(times);
        //then
        assertThat(statistic).containsExactly(Time.valueOf("00:00:30"), Time.valueOf("00:02:00"));
    }

    private TimeMatrix givenTimeMatrix() {
        List<Time> firstRow = new ArrayList<>();
        firstRow.add(Time.valueOf("00:00:20"));
        firstRow.add(Time.valueOf("00:01:00"));
        List<Time> secondRow = new ArrayList<>();
        secondRow.add(Time.valueOf("00:00:40"));
        secondRow.add(Time.valueOf("00:03:00"));
        TimeMatrix times = new TimeMatrix();
        times.addRow(firstRow);
        times.addRow(secondRow);
        return times;
    }
}
