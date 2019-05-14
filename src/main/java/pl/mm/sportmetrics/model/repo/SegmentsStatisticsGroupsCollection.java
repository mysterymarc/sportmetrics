package pl.mm.sportmetrics.model.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SegmentsStatisticsGroupsCollection implements Iterable<SegmentsStatisticsGroup> {

    private List<SegmentsStatisticsGroup> segmentsStatisticsGroups = new ArrayList<SegmentsStatisticsGroup>();

    public boolean add(SegmentsStatisticsGroup rowsGroup){
        return segmentsStatisticsGroups.add(rowsGroup);
    }

    @Override
    public Iterator<SegmentsStatisticsGroup> iterator() {
        return segmentsStatisticsGroups.iterator();
    }

    public SegmentsStatisticsGroup getGroup(int i){
        return segmentsStatisticsGroups.get(i);
    }
}
