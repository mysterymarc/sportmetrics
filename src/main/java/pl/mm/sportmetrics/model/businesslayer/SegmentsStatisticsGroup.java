package pl.mm.sportmetrics.model.businesslayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SegmentsStatisticsGroup implements Iterable<SegmentsStatistic> {

    private List<SegmentsStatistic> allSegmentStatistics = new ArrayList<SegmentsStatistic>();


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


}
