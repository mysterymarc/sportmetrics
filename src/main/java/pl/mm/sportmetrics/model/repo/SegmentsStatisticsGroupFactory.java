package pl.mm.sportmetrics.model.repo;

import pl.mm.sportmetrics.statistics.Calculation;

public class SegmentsStatisticsGroupFactory {

    public SegmentsStatisticsGroup create(ResultsForRunnersGroup resultsForRunnersGroup){

        SegmentsStatisticsGroup rowsGroup = new SegmentsStatisticsGroup();
        rowsGroup.add(new Calculation().getAvgFromResults(resultsForRunnersGroup));
            //if more statistics should be calculated - it should be implemented here as: rowsGroup.add(new Calculation().getStatisticFromResults())
        return rowsGroup;
    }

}
