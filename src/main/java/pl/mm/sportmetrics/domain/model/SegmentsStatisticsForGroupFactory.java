package pl.mm.sportmetrics.domain.model;

import pl.mm.sportmetrics.domain.logic.Calculation;
import pl.mm.sportmetrics.domain.logic.TimeList;
import pl.mm.sportmetrics.domain.logic.TimeMatrix;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class SegmentsStatisticsForGroupFactory {

    private final List<Calculation> calculationStrategies;

    public SegmentsStatisticsForGroupFactory(List<Calculation> calculationStrategies){
        this.calculationStrategies = calculationStrategies;
    }

    public SegmentsStatisticsForGroup getObject(ResultsForRunnersGroup resultsForRunnersGroup){

        SegmentsStatisticsForGroup statisticsForGroup = new SegmentsStatisticsForGroup();

        for(Calculation calculation : calculationStrategies){
            TimeList result = calculation.getStatistic(mapBusinessObjectToTimeMatrix(resultsForRunnersGroup));
            statisticsForGroup.add(mapTimeListToBusinessObject(result,calculation.getName()));
        }

        return statisticsForGroup;
    }

    private TimeMatrix mapBusinessObjectToTimeMatrix(ResultsForRunnersGroup results){
        TimeMatrix matrix = new TimeMatrix();
        for(ResultsForRunner runnerResult : results.resultsForRunners){
            List<Time> runnerTimes = new ArrayList<>();
            for(Result segmentResult : runnerResult.getSegmentResults()){
                runnerTimes.add(segmentResult.getTime());
            }
            matrix.addRow(runnerTimes);
        }
        return matrix;
    }

    private SegmentsStatistic mapTimeListToBusinessObject(TimeList timeList, String statisticName){
        SegmentsStatistic statistic = new SegmentsStatistic();
        statistic.setTitle(statisticName);
        for(Time time : timeList){
            statistic.addStatistic(time);
        }
        return statistic;
    }



}
