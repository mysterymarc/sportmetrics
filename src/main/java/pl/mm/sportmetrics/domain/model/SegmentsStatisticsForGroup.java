package pl.mm.sportmetrics.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

}
