package pl.mm.sportmetrics.model.viewlayer;

import java.util.ArrayList;
import java.util.List;

public class AnalysisResultsGroupView {

    List<AnalysisResultRow> analyses = new ArrayList<AnalysisResultRow>();

    public List<AnalysisResultRow> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<AnalysisResultRow> analyses) {
        this.analyses = analyses;
    }

    public boolean add(AnalysisResultRow analysis){
        return analyses.add(analysis);
    }
}
