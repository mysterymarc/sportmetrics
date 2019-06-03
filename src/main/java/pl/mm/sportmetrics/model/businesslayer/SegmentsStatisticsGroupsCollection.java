package pl.mm.sportmetrics.model.businesslayer;

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

    public void evaluateStatisticsWithWinLossDescriptions(int firstGroupIndex, int secondGroupIndex){
        for(int i=0;i < segmentsStatisticsGroups.get(firstGroupIndex).getNumberOfStatistics(); i++) {
            SegmentsStatistic firstStatistic = segmentsStatisticsGroups.get(firstGroupIndex).getRow(i);
            SegmentsStatistic secondStatistic = segmentsStatisticsGroups.get(secondGroupIndex).getRow(i);
            firstStatistic.evaluateStatisticsWithWinLossDescriptions(secondStatistic);
            secondStatistic.evaluateStatisticsWithWinLossDescriptions(firstStatistic);
        }
    }


}
