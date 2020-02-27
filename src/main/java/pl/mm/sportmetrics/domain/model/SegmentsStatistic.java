package pl.mm.sportmetrics.domain.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SegmentsStatistic {
    private String title;
    private List<SingleStatistic> segmentSingleStatistics = new ArrayList<>();

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

    public void addStatistic(Time value, Score description){
        SingleStatistic singleStatistic = new SingleStatistic(value,description);
        segmentSingleStatistics.add(singleStatistic);
    }

    public void addStatistic(String time){
        SingleStatistic singleStatistic = new SingleStatistic(time);
        segmentSingleStatistics.add(singleStatistic);
    }

    public void addStatistic(String time, Score description){
        SingleStatistic singleStatistic = new SingleStatistic(Time.valueOf(time),description);
        segmentSingleStatistics.add(singleStatistic);
    }


    private SingleStatistic getStatistic(int segmentNumber){
        return segmentSingleStatistics.get(segmentNumber);
    }

    public void evaluateStatisticsWithWinLossDescriptions(SegmentsStatistic comparedTo){
        for (int i = 0; i < segmentSingleStatistics.size(); i++) {
            getStatistic(i).evaluateDescriptionWithWinLossByComparison(comparedTo.getStatistic(i));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentsStatistic that = (SegmentsStatistic) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(segmentSingleStatistics, that.segmentSingleStatistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, segmentSingleStatistics);
    }

    @Override
    public String toString() {
        return "SegmentsStatistic{" +
                "title='" + title + '\'' +
                ", segmentSingleStatistics=" + segmentSingleStatistics +
                '}';
    }
}
