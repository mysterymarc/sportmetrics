package pl.mm.sportmetrics.model.viewlayer;

import pl.mm.sportmetrics.model.businesslayer.Result;
import pl.mm.sportmetrics.model.businesslayer.ResultsForRunner;
import pl.mm.sportmetrics.model.businesslayer.ResultsForRunnersGroup;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ResultsMatrixFromBusinessToViewMapper {

    private RowResultsGroupView resultRows = new RowResultsGroupView();


    public RowResultsGroupView getResultsMatrix() {
        return resultRows;
    }

    public void doMapping(ResultsForRunnersGroup resultsForRunnersGroup) {

        for (ResultsForRunner modelResultsForRunner : resultsForRunnersGroup) {
            RowResultView viewScoresRow = new RowResultView();
            viewScoresRow.setCompetitorId(modelResultsForRunner.getCompetitorId());
            viewScoresRow.setCompetitorName(modelResultsForRunner.getCompetitorName());
            viewScoresRow.setCompetitorCity(modelResultsForRunner.getCompetitorCity());
            viewScoresRow.setTotalResultId(modelResultsForRunner.getTotalResultId());
            viewScoresRow.setDelayTime(addFrontPlus(mapTimeToViewForm(modelResultsForRunner.getDelayTime())));
            viewScoresRow.setTotalTime(mapTimeToViewForm(modelResultsForRunner.getTotalTime()));
            viewScoresRow.setPosition(mapPositionToViewForm(modelResultsForRunner.getPosition()));
            viewScoresRow.setSegmentResults(mapModelScoreToViewScore(modelResultsForRunner.getSegmentResults()));
            viewScoresRow.setCumulativeResults(mapModelScoreToViewScore(modelResultsForRunner.getCumulativeResults()));
            resultRows.add(viewScoresRow);
        }
    }

    private List<SingleResultView> mapModelScoreToViewScore(List<Result> modelResults){
        List<SingleResultView> viewScore = new ArrayList<>();
        for (Result singleResult : modelResults) {
            viewScore.add(new SingleResultView(
                    mapTimeToViewForm(singleResult.getTime()),
                    mapPositionToViewForm(singleResult.getPosition())));
        }
        return viewScore;
    }

    private String mapTimeToViewForm(Time sourceTime) {
        String time = sourceTime.toString();
        if (time.equals("00:00:00")) {
            time = "";
        }
        if (time.startsWith("00:") && time.length() == 8) {
            time = time.substring(3);
        }
        return time;
    }

    private String mapPositionToViewForm(int position) {
        if (position == -1) {
            return "x";
        }
        return String.valueOf(position);
    }

    private String addFrontPlus(String time){
        if(!time.equals("")){
            return "+" + time;
        }
        return time;
    }
}
