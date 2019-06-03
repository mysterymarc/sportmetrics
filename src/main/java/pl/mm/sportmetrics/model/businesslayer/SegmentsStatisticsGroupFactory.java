package pl.mm.sportmetrics.model.businesslayer;

import pl.mm.sportmetrics.logic.Calculation;

public class SegmentsStatisticsGroupFactory {

    public SegmentsStatisticsGroup getObject(ResultsForRunnersGroup resultsForRunnersGroup){

        SegmentsStatisticsGroup rowsGroup = new SegmentsStatisticsGroup();
        rowsGroup.add(new Calculation().getAvgFromResults(resultsForRunnersGroup));
            //if additional statistics should be calculated it should be implemented here as: rowsGroup.add(new Calculation().getMedianFromResults())
        return rowsGroup;
    }

}
