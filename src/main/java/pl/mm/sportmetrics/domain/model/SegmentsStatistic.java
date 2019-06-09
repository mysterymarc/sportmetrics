package pl.mm.sportmetrics.domain.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class SegmentsStatistic {
    private String title;
    private List<SingleStatistic> segmentSingleStatistics = new ArrayList<SingleStatistic>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SingleStatistic> getSegmentSingleStatistics() {
        return segmentSingleStatistics;
    }

    public void setSegmentSingleStatistics(List<SingleStatistic> segmentSingleStatistics) {
        this.segmentSingleStatistics = segmentSingleStatistics;
    }

    public void addStatistic(Time value){
        SingleStatistic singleStatistic = new SingleStatistic(value);
        segmentSingleStatistics.add(singleStatistic);
    }

    public void addStatistic(Time value, String description){
        SingleStatistic singleStatistic = new SingleStatistic(value,description);
        segmentSingleStatistics.add(singleStatistic);
    }

    public int segmentsNumber(){
        return segmentSingleStatistics.size();
    }

    public SingleStatistic getStatistic(int segmentNumber){
        return segmentSingleStatistics.get(segmentNumber);
    }

    public void evaluateStatisticsWithWinLossDescriptions(SegmentsStatistic comparedTo){
        for (int i = 0; i < segmentsNumber(); i++) {
            getStatistic(i).evaluateDescriptionWithWinLossByComparison(comparedTo.getStatistic(i));
        }
    }

}
