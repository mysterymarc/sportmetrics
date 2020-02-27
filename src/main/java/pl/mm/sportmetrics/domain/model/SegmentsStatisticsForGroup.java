package pl.mm.sportmetrics.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SegmentsStatisticsForGroup implements Iterable<SegmentsStatistic> {

    private final List<SegmentsStatistic> allSegmentStatistics = new ArrayList<>();
    public boolean add(SegmentsStatistic row){
        return allSegmentStatistics.add(row);
    }

    @Override
    public Iterator<SegmentsStatistic> iterator() {
        return allSegmentStatistics.iterator();
    }

    public SegmentsStatistic getRow(int i){
        return allSegmentStatistics.get(i);
    }

    public int getNumberOfStatistics(){
        return allSegmentStatistics.size();
    }

    @Override
    public String toString() {
        return "SegmentsStatisticsForGroup{" +
                "allSegmentStatistics=" + allSegmentStatistics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentsStatisticsForGroup that = (SegmentsStatisticsForGroup) o;
        return Objects.equals(allSegmentStatistics, that.allSegmentStatistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allSegmentStatistics);
    }
}
