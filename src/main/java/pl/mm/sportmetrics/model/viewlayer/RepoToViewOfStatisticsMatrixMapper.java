package pl.mm.sportmetrics.model.viewlayer;

import pl.mm.sportmetrics.model.businesslayer.SegmentsStatistic;
import pl.mm.sportmetrics.model.businesslayer.SegmentsStatisticsGroup;
import pl.mm.sportmetrics.model.businesslayer.SingleStatistic;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class RepoToViewOfStatisticsMatrixMapper {

    private AnalysisResultsGroupView analysisRows = new AnalysisResultsGroupView();

    public AnalysisResultsGroupView getResultsMatrix() {
        return analysisRows;
    }


    public void doMapping(SegmentsStatisticsGroup segmentsStatisticsGroup) {

        for (SegmentsStatistic modelStatisticRow : segmentsStatisticsGroup) {
            AnalysisResultRow viewRow = new AnalysisResultRow();
            viewRow.setTitle(modelStatisticRow.getTitle());
            viewRow.setSegmentResults(mapModelStatToViewStat(modelStatisticRow.getSegmentSingleStatistics()));
            analysisRows.add(viewRow);
        }
    }

    private List<AnalysisResultForSegment> mapModelStatToViewStat(List<SingleStatistic> modelStats){
        List<AnalysisResultForSegment> viewStats = new ArrayList<>();
        for (SingleStatistic singleStat : modelStats) {
            viewStats.add(new AnalysisResultForSegment(
                    mapTimeToViewForm(singleStat.getValue()),
                    singleStat.getDescription()));
        }
        return viewStats;
    }


    private String mapTimeToViewForm(Time sourceTime){
        String time = sourceTime.toString();
        if(time.equals("00:00:00")){
            time = "";
        }
        if(time.startsWith("00:") && time.length() == 8){
            time = time.substring(3);
        }
        return time;
    }
}