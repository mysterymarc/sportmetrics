package pl.mm.sportmetrics.domain.logic;

import org.junit.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeMatrixTest {

    TimeMatrix timeMatrix;

    @Test
    public void givenTimeMatrixWhenGetColumnThenProperListOfTimesReturned(){
        givenTimeMatrix();

        List<TimeList> timesColumns = timeMatrix.getColumns();

        assertThat(timesColumns).isEqualTo(expectedColumns());
    }

    private void givenTimeMatrix(){
        timeMatrix = new TimeMatrix();
        timeMatrix.addRow(Arrays.asList(Time.valueOf("00:01:00"), Time.valueOf("00:02:00")));
        timeMatrix.addRow(Arrays.asList(Time.valueOf("00:03:00"), Time.valueOf("00:04:00")));
        timeMatrix.addRow(Arrays.asList(Time.valueOf("00:05:00"), Time.valueOf("00:06:00")));
    }

    private List<TimeList> expectedColumns(){
        List<TimeList> columns = new ArrayList<>();
        TimeList column1 = new TimeList(Arrays.asList(Time.valueOf("00:01:00"),Time.valueOf("00:03:00"),Time.valueOf("00:05:00")));
        TimeList column2 = new TimeList(Arrays.asList(Time.valueOf("00:02:00"),Time.valueOf("00:04:00"),Time.valueOf("00:06:00")));
        columns.add(column1);
        columns.add(column2);
        return columns;
    }
}