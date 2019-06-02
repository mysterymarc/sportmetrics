package pl.mm.sportmetrics.model.viewlayer;
import pl.mm.sportmetrics.model.businesslayer.Competition;
import pl.mm.sportmetrics.model.businesslayer.Segments;

public class ResultsPageDTO {

    Competition competition;
    Segments segments;
    RowResultsGroupView resultRows;

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
}
