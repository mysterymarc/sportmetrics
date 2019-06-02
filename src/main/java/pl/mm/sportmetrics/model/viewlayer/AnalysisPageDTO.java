package pl.mm.sportmetrics.model.viewlayer;

import pl.mm.sportmetrics.model.businesslayer.Competition;
import pl.mm.sportmetrics.model.businesslayer.Segments;

public class AnalysisPageDTO {

    Competition competition;
    Segments segments;
    RowResultsGroupsColletionView results;
    AnalysisResultsGroupsCollectionView analyses;

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

    public AnalysisResultsGroupsCollectionView getAnalyses() {
        return analyses;
    }

    public void setAnalyses(AnalysisResultsGroupsCollectionView analyses) {
        this.analyses = analyses;
    }
}
