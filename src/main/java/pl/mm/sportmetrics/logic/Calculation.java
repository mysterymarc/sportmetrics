package pl.mm.sportmetrics.logic;

import pl.mm.sportmetrics.model.businesslayer.Result;
import pl.mm.sportmetrics.model.businesslayer.ResultsForRunnersGroup;
import pl.mm.sportmetrics.model.businesslayer.SegmentsStatistic;
import pl.mm.sportmetrics.model.businesslayer.SingleStatistic;
import pl.mm.sportmetrics.repository.entity.PartialResultEntity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Calculation {

    public SegmentsStatistic getAvgFromResults(ResultsForRunnersGroup resultsForRunnersGroup) {
        SegmentsStatistic avg = new SegmentsStatistic();
        avg.setTitle("SegmentEntity Average Time");
        List<Long> tmpSegmentCalculation = new ArrayList<>();

        int segmentsNumber = resultsForRunnersGroup.getSegmentsNumber();
        int competitorsNumber = resultsForRunnersGroup.getRowsNumber();

        for (int i = 0; i < segmentsNumber; i++) {
            tmpSegmentCalculation.add(0L);
        }

        for (int j = 0; j < segmentsNumber; j++) {

            List<Result> scoresForSegment = resultsForRunnersGroup.getAllCompetitorsSingleSegmentScores(j);

            for (int i = 0; i < competitorsNumber; i++) {
                Time singleTime = scoresForSegment.get(i).getTime();
                if (singleTime.equals(Time.valueOf("00:00:00"))) {
                    tmpSegmentCalculation.set(j, Time.valueOf("00:00:00").getTime());
                    break;
                } else {
                    tmpSegmentCalculation.set(j, tmpSegmentCalculation.get(j) + singleTime.getTime());
                }
            }
        }

        for (int i = 0; i < segmentsNumber; i++) {
            if (tmpSegmentCalculation.get(i).equals(Time.valueOf("00:00:00").getTime())) {
                avg.addStatistic(Time.valueOf("00:00:00"));
            } else {
                avg.addStatistic(new Time(tmpSegmentCalculation.get(i) / competitorsNumber));
            }
        }

        return avg;

    }

    public void setWinLossAttributeToStatisticsByItsComparison(SegmentsStatistic firstRow, SegmentsStatistic secondRow) {

        for (int i = 0; i < firstRow.segmentsNumber(); i++) {
            SingleStatistic first = firstRow.getStatistic(i);
            SingleStatistic second = secondRow.getStatistic(i);
            if (first.getValue().equals(Time.valueOf("00:00:00")) && second.getValue().equals(Time.valueOf("00:00:00"))) {
                first.setDescription("draw");
                second.setDescription("draw");
            } else if (first.getValue().equals(Time.valueOf("00:00:00"))) {
                first.setDescription("loss");
                second.setDescription("win");
            } else if (second.getValue().equals(Time.valueOf("00:00:00"))) {
                first.setDescription("win");
                second.setDescription("loss");
            } else if (first.getValue().getTime() > second.getValue().getTime()) {
                first.setDescription("loss");
                second.setDescription("win");
            } else if (first.getValue().getTime() < second.getValue().getTime()) {
                first.setDescription("win");
                second.setDescription("loss");
            } else if (first.getValue().getTime() == second.getValue().getTime()) {
                first.setDescription("draw");
                second.setDescription("draw");
            } else {
                first.setDescription("error");
                second.setDescription("error");
            }
        }
    }

}
