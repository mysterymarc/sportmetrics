package pl.mm.sportmetrics.domain.logic;

import org.junit.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MedianTest {

    @Test
    public void givenTimeMatrixWhenMedianAppliedOnMatrixThenCalculatedCorrectMedianByColumns() {
        TimeMatrix times = givenTimeMatrix();

        TimeList statistic = new Median().getStatistic(times);

        assertThat(statistic).containsExactly(Time.valueOf("00:00:30"), Time.valueOf("00:00:15"), Time.valueOf("00:03:00"));
    }

    private TimeMatrix givenTimeMatrix() {
        List<Time> firstRow = new ArrayList<>();
        firstRow.add(Time.valueOf("00:00:10"));
        firstRow.add(Time.valueOf("00:00:10"));
        firstRow.add(Time.valueOf("00:01:00"));
        List<Time> secondRow = new ArrayList<>();
        secondRow.add(Time.valueOf("00:00:40"));
        secondRow.add(Time.valueOf("00:00:00"));
        secondRow.add(Time.valueOf("00:03:00"));
        List<Time> thirdRow = new ArrayList<>();
        thirdRow.add(Time.valueOf("00:00:30"));
        thirdRow.add(Time.valueOf("00:00:20"));
        thirdRow.add(Time.valueOf("02:00:00"));

        TimeMatrix times = new TimeMatrix();
        times.addRow(firstRow);
        times.addRow(secondRow);
        times.addRow(thirdRow);
        return times;
    }
}