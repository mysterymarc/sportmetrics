package pl.mm.sportmetrics.dto.viewlayer;

import pl.mm.sportmetrics.domain.model.Competition;
import pl.mm.sportmetrics.domain.model.Segments;

import java.util.Objects;

public class AnalysisPageDTO {

    Competition competition;
    Segments segments;
    RowResultsGroupsColletionView results;
    AnalysisResultsGroupsCollectionView analyses;

    public AnalysisPageDTO() {
    }

    public AnalysisPageDTO(Competition competition, Segments segments, RowResultsGroupsColletionView results, AnalysisResultsGroupsCollectionView analyses) {
        this.competition = competition;
        this.segments = segments;
        this.results = results;
        this.analyses = analyses;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisPageDTO that = (AnalysisPageDTO) o;
        return Objects.equals(competition, that.competition) &&
                Objects.equals(segments, that.segments) &&
                Objects.equals(results, that.results) &&
                Objects.equals(analyses, that.analyses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competition, segments, results, analyses);
    }

    @Override
    public String toString() {
        return "AnalysisPageDTO{" +
                "competition=" + competition +
                ", segments=" + segments +
                ", results=" + results +
                ", analyses=" + analyses +
                '}';
    }
}
