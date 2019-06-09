package pl.mm.sportmetrics.dto.viewlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnalysisResultsGroupsCollectionView {

    private List<AnalysisResultsGroupView> analysesGroup = new ArrayList<>();

    public AnalysisResultsGroupsCollectionView() {
    }

    public AnalysisResultsGroupsCollectionView(List<AnalysisResultsGroupView> analysesGroup) {
        this.analysesGroup = analysesGroup;
    }

    public List<AnalysisResultsGroupView> getAnalysesGroup() {
        return analysesGroup;
    }

    public void setAnalysesGroup(List<AnalysisResultsGroupView> analysesGroup) {
        this.analysesGroup = analysesGroup;
    }

    public boolean add(AnalysisResultsGroupView analysis){
        return analysesGroup.add(analysis);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisResultsGroupsCollectionView that = (AnalysisResultsGroupsCollectionView) o;
        return Objects.equals(analysesGroup, that.analysesGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(analysesGroup);
    }

    @Override
    public String toString() {
        return "AnalysisResultsGroupsCollectionView{" +
                "analysesGroup=" + analysesGroup +
                '}';
    }
}
