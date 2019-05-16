package pl.mm.sportmetrics.model.viewobject;

import pl.mm.sportmetrics.model.database.Competition;
import pl.mm.sportmetrics.model.database.Segment;
import pl.mm.sportmetrics.model.repo.Segments;

import java.util.List;

public class AnalysisPageDataView {

    Competition competition;
    Segments segments;
    RowResultsGroupsColletionView results;
    List<List<AnalysisResultRow>> avgAnalysis;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Segments getSegments() {
        return segments;
    }

    public void setSegments(Segments segments) {
        this.segments = segments;
    }

    public RowResultsGroupsColletionView getResults() {
        return results;
    }

    public void setResults(RowResultsGroupsColletionView results) {
        this.results = results;
    }

    public List<List<AnalysisResultRow>> getAvgAnalysis() {
        return avgAnalysis;
    }

    public void setAvgAnalysis(List<List<AnalysisResultRow>> avgAnalysis) {
        this.avgAnalysis = avgAnalysis;
    }
}
