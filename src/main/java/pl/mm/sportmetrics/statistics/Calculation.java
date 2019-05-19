package pl.mm.sportmetrics.statistics;

import pl.mm.sportmetrics.repository.entity.PartialResult;
import pl.mm.sportmetrics.model.repo.*;

import java.sql.Time;

import java.util.ArrayList;
import java.util.List;

public class Calculation {


    public List<Time> getAvgFromResults(List<List<PartialResult>> all) {

        List<Time> avg = new ArrayList<>();

        List<Long> tmpSegmentCalculation = new ArrayList<>();

        int segmentsNumber = all.get(0).size();
        int competitorsNumber = all.size();

        for (int i = 0; i < segmentsNumber; i++) {
            tmpSegmentCalculation.add(0L);
        }

        for (int j = 0; j < segmentsNumber; j++) {
            for (int i = 0; i < competitorsNumber; i++) {
                Time singleTime = all.get(i).get(j).segmentTime;
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
                avg.add(Time.valueOf("00:00:00"));
            } else {
                avg.add(new Time(tmpSegmentCalculation.get(i) / competitorsNumber));
            }
        }

        return avg;
    }


    public SegmentsStatistic getAvgFromResults(ResultsForRunnersGroup resultsForRunnersGroup) {
        SegmentsStatistic avg = new SegmentsStatistic();
        avg.setTitle("Segment Average Time");
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


    public List<String> getInterpretationOfTimeListsComparison(List<Time> compareThis, List<Time> compareTo) {

        List<String> result = new ArrayList<>();

        for (int i = 0; i < compareThis.size(); i++) {
            if (compareThis.get(i).equals(Time.valueOf("00:00:00")) && compareTo.get(i).equals(Time.valueOf("00:00:00"))) {
                result.add("draw");
            } else if (compareThis.get(i).equals(Time.valueOf("00:00:00"))) {
                result.add("loss");
            } else if (compareTo.get(i).equals(Time.valueOf("00:00:00"))) {
                result.add("win");
            } else if (compareThis.get(i).getTime() > compareTo.get(i).getTime()) {
                result.add("loss");
            } else if (compareThis.get(i).getTime() < compareTo.get(i).getTime()) {
                result.add("win");
            } else if (compareThis.get(i).getTime() == compareTo.get(i).getTime()) {
                result.add("draw");
            } else {
                result.add("error");
            }
        }

        return result;
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
