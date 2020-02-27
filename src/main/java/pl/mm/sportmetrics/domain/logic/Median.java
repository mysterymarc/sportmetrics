package pl.mm.sportmetrics.domain.logic;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

public class Median implements Calculation{

    @Override
    public TimeList getStatistic(TimeMatrix matrix) {
        TimeList result = new TimeList();

        for(TimeList column : matrix.getColumns()) {
            List<Time> sortedTimes = column.getRow().stream()
                    .filter(time -> !time.toString().equals("00:00:00"))
                    .sorted()
                    .collect(Collectors.toList());
            result.add(getMiddleElement(sortedTimes));
        }
        return result;
    }

    @Override
    public String getName(){
        return "Median Time";
    }

    private Time getMiddleElement(List<Time> times){
        int listSize = times.size();
        if(listSize % 2 == 0){
            Time elementBeforeMiddle = times.get(listSize / 2 - 1);
            Time elementAfterMiddle = times.get(listSize / 2);
            long middleValue = (elementBeforeMiddle.getTime() + elementAfterMiddle.getTime()) / 2;
            return new Time(middleValue);
        } else {
            return times.get(listSize / 2);
        }
    }
}
