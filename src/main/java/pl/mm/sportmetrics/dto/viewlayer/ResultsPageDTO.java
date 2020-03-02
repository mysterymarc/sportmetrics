package pl.mm.sportmetrics.dto.viewlayer;
import pl.mm.sportmetrics.domain.model.Competition;
import pl.mm.sportmetrics.domain.model.Segments;

import java.util.Objects;

public class ResultsPageDTO {

    private Competition competition;
    private Segments segments;
    private RowResultsGroupView resultRows;

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

    public RowResultsGroupView getResultRows() {
        return resultRows;
    }

    public void setResultRows(RowResultsGroupView resultRows) {
        this.resultRows = resultRows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultsPageDTO that = (ResultsPageDTO) o;
        return Objects.equals(competition, that.competition) &&
                Objects.equals(segments, that.segments) &&
                Objects.equals(resultRows, that.resultRows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competition, segments, resultRows);
    }

    @Override
    public String toString() {
        return "ResultsPageDTO{" +
                "competition=" + competition +
                ", segments=" + segments +
                ", resultRows=" + resultRows +
                '}';
    }
}
