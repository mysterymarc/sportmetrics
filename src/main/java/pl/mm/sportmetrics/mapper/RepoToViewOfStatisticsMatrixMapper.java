package pl.mm.sportmetrics.mapper;

import pl.mm.sportmetrics.model.repo.SingleStatistic;
import pl.mm.sportmetrics.model.repo.SegmentsStatistic;
import pl.mm.sportmetrics.model.repo.SegmentsStatisticsGroup;
import pl.mm.sportmetrics.model.viewobject.AnalysisResultForSegment;
import pl.mm.sportmetrics.model.viewobject.AnalysisResultRow;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class RepoToViewOfStatisticsMatrixMapper {

    private List<AnalysisResultRow> analysisRows;

    public RepoToViewOfStatisticsMatrixMapper() {
        this.analysisRows = new ArrayList<>();
    }

    public List<AnalysisResultRow> getResultsMatrix() {
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
