package pl.mm.sportmetrics.dto.viewlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnalysisResultRow {

    private String title;
    private List<AnalysisResultForSegment> segmentResults;

    public AnalysisResultRow() {
        this.segmentResults = new ArrayList<>();
    }

    public AnalysisResultRow(String title, List<AnalysisResultForSegment> segmentResults){
        this.title = title;
        this.segmentResults = segmentResults;
    }

    public List<AnalysisResultForSegment> getSegmentResults() {
        return segmentResults;
    }

    public void setSegmentResults(List<AnalysisResultForSegment> segmentResults) {
        this.segmentResults = segmentResults;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisResultRow that = (AnalysisResultRow) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(segmentResults, that.segmentResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, segmentResults);
    }

    @Override
    public String toString() {
        return "AnalysisResultRow{" +
                "title='" + title + '\'' +
                ", segmentResults=" + segmentResults +
                '}';
    }
}
