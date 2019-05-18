package pl.mm.sportmetrics.model.viewobject;

import pl.mm.sportmetrics.model.database.Competition;
import pl.mm.sportmetrics.model.database.Segment;
import pl.mm.sportmetrics.model.repo.Segments;

public class AnalysisPageDataView {

    Competition competition;
    Segments segments;
    RowResultsGroupsColletionView results;
    AnalysisResultsGroupsCollectionView avgAnalysis;

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

    public AnalysisResultsGroupsCollectionView getAvgAnalysis() {
        return avgAnalysis;
    }

    public void setAvgAnalysis(AnalysisResultsGroupsCollectionView avgAnalysis) {
        this.avgAnalysis = avgAnalysis;
    }
}
