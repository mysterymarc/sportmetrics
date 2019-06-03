package pl.mm.sportmetrics.logic;

import pl.mm.sportmetrics.model.businesslayer.*;
import pl.mm.sportmetrics.repository.entity.PartialResultEntity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Calculation {

    public SegmentsStatistic getAvgFromResults(ResultsForRunnersGroup resultsForRunnersGroup) {

        int competitorsNumber = resultsForRunnersGroup.getRowsNumber();

        List<Long> partialCalculation = sumupRunnersTimesForEachSegmentSeparately(resultsForRunnersGroup);
        SegmentsStatistic avg = divideTimesForEachSegmentSeparatelyByCompetitorsNumber(partialCalculation,competitorsNumber);

        avg.setTitle("Segment Average Time");
        return avg;
    }

    private List<Long> initiateListWithZeros(int size){
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(0L);
        }
        return list;
    }

    private List<Long> sumupRunnersTimesForEachSegmentSeparately(ResultsForRunnersGroup results){
        int segmentsNumber = results.getSegmentsNumber();
        int competitorsNumber = results.getRowsNumber();

        List<Long> sumupCalculation = initiateListWithZeros(segmentsNumber);

        for (int j = 0; j < segmentsNumber; j++) {

            List<Result> scoresForSegment = results.getAllCompetitorsSingleSegmentScores(j);

            for (int i = 0; i < competitorsNumber; i++) {
                Time singleTime = scoresForSegment.get(i).getTime();
                if (singleTime.equals(Time.valueOf("00:00:00"))) {
                    sumupCalculation.set(j, Time.valueOf("00:00:00").getTime());
                    break;
                } else {
                    sumupCalculation.set(j, sumupCalculation.get(j) + singleTime.getTime());
                }
            }
        }
        return sumupCalculation;
    }

    private SegmentsStatistic divideTimesForEachSegmentSeparatelyByCompetitorsNumber(List<Long> sumOfSegmentTimes, int competitorsNumber){
        SegmentsStatistic avg = new SegmentsStatistic();
        for (int i = 0; i < sumOfSegmentTimes.size(); i++) {
            if (sumOfSegmentTimes.get(i).equals(Time.valueOf("00:00:00").getTime())) {
                avg.addStatistic(Time.valueOf("00:00:00"));
            } else {
                avg.addStatistic(new Time(sumOfSegmentTimes.get(i) / competitorsNumber));
            }
        }
        return avg;
    }

}
