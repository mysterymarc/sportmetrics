package pl.mm.sportmetrics.model.viewobject;

import pl.mm.sportmetrics.model.database.Competition;
import pl.mm.sportmetrics.model.database.Segment;

import java.util.List;

public class ResultsPageDataView {

    Competition competition;
    List<Segment> segments;
    RowResultsGroupView resultRows;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public RowResultsGroupView getResultRows() {
        return resultRows;
    }

    public void setResultRows(RowResultsGroupView resultRows) {
        this.resultRows = resultRows;
    }
}
