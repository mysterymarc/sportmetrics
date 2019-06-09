package pl.mm.sportmetrics.dto.viewlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisResultsGroupView that = (AnalysisResultsGroupView) o;
        return Objects.equals(analyses, that.analyses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(analyses);
    }

    @Override
    public String toString() {
        return "AnalysisResultsGroupView{" +
                "analyses=" + analyses +
                '}';
    }
}
