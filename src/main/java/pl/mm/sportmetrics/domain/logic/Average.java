package pl.mm.sportmetrics.domain.logic;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Average implements Calculation{

    @Override
    public TimeList getStatistic(TimeMatrix matrix) {
        List<Long> columnsSums = sumUpTimesForEachColumnSeparately(matrix);
        return divideTimesForEachColumnSeparatelyByRowsNumber(columnsSums,matrix.getRowsNumber());
    }

    @Override
    public String getStatisticName(){
        return "Average Time";
    }

    private List<Long> sumUpTimesForEachColumnSeparately(TimeMatrix matrix){

        List<Long> sum = new ArrayList<>();

        for(TimeList column : matrix.getColumns())
        {
            long columnSum = 0L;
            for(Time element : column){
                if(element.equals(Time.valueOf("00:00:00"))){ // if zero met in cell it means data error then no sense to count statistic
                    columnSum = Time.valueOf("00:00:00").getTime();
                    break;
                } else {
                    columnSum += element.getTime();
                }
            }
            sum.add(columnSum);
        }

        return sum;
    }

    private TimeList divideTimesForEachColumnSeparatelyByRowsNumber(List<Long> columnSums, int rowNumber){
        TimeList avg = new TimeList();
        for (Long columnSum : columnSums) {
            if (columnSum.equals(Time.valueOf("00:00:00").getTime())) {
                avg.add("00:00:00");
            } else {
                avg.add(new Time(columnSum / rowNumber));
            }
        }
        return avg;
    }

}
