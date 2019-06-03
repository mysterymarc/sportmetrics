package pl.mm.sportmetrics.model.viewlayer;

import java.util.ArrayList;
import java.util.List;

public class AnalysisResultRow {

    private String title;
    private List<AnalysisResultForSegment> segmentResults;

    public AnalysisResultRow() {
        this.segmentResults = new ArrayList<>();
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

}
