package pl.mm.sportmetrics.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SegmentsStatisticsForGroupsCollection implements Iterable<SegmentsStatisticsForGroup> {

    private final List<SegmentsStatisticsForGroup> segmentsStatisticsForGroups = new ArrayList<>();

    public boolean add(SegmentsStatisticsForGroup rowsGroup){
        return segmentsStatisticsForGroups.add(rowsGroup);
    }

    @Override
    public Iterator<SegmentsStatisticsForGroup> iterator() {
        return segmentsStatisticsForGroups.iterator();
    }

    public SegmentsStatisticsForGroup getGroup(int i){
        return segmentsStatisticsForGroups.get(i);
    }

    public void evaluateStatisticsWithWinLossDescriptions(int firstGroupIndex, int secondGroupIndex){
        for(int i = 0; i < segmentsStatisticsForGroups.get(firstGroupIndex).getNumberOfStatistics(); i++) {
            SegmentsStatistic firstStatistic = segmentsStatisticsForGroups.get(firstGroupIndex).getRow(i);
            SegmentsStatistic secondStatistic = segmentsStatisticsForGroups.get(secondGroupIndex).getRow(i);
            firstStatistic.evaluateStatisticsWithWinLossDescriptions(secondStatistic);
            secondStatistic.evaluateStatisticsWithWinLossDescriptions(firstStatistic);
        }
    }


}
