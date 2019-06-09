package pl.mm.sportmetrics.domain.logic;

import java.sql.Time;

public class Median implements Calculation{

    @Override
    public TimeList getStatistic(TimeMatrix matrix) {
        return median(matrix);
    }

    @Override
    public String getStatisticName(){
        return "Median Time";
    }

    private TimeList median(TimeMatrix matrix) {

        TimeList result = new TimeList();

        for(int i=0; i < matrix.getColumns().size() ; i++){
            result.add(Time.valueOf("00:01:00"));
        }

        return result;
    }

}
