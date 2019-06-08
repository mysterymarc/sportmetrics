package pl.mm.sportmetrics.model.businesslayer;

import pl.mm.sportmetrics.logic.Calculation;
import pl.mm.sportmetrics.logic.TimeList;
import pl.mm.sportmetrics.logic.TimeMatrix;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class SegmentsStatisticsForGroupFactory {

    public List<Calculation> calculationStrategies;

    public SegmentsStatisticsForGroupFactory(List<Calculation> calculationStrategies){
        this.calculationStrategies = calculationStrategies;
    }

    public SegmentsStatisticsForGroup getObject(ResultsForRunnersGroup resultsForRunnersGroup){

        SegmentsStatisticsForGroup statisticsForGroup = new SegmentsStatisticsForGroup();

        for(Calculation calculation : calculationStrategies){
            TimeList result = calculation.getStatistic(mapBusinessObjectToTimeMatrix(resultsForRunnersGroup));
            statisticsForGroup.add(mapTimeListToBusinessObject(result,calculation.getStatisticName()));
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
