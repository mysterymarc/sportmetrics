package pl.mm.sportmetrics.model.viewlayer;

import java.util.ArrayList;
import java.util.List;

public class AnalysisResultsGroupsCollectionView {

    List<AnalysisResultsGroupView> analysesGroup = new ArrayList<AnalysisResultsGroupView>();

    public List<AnalysisResultsGroupView> getAnalysesGroup() {
        return analysesGroup;
    }

    public void setAnalysesGroup(List<AnalysisResultsGroupView> analysesGroup) {
        this.analysesGroup = analysesGroup;
    }

    public boolean add(AnalysisResultsGroupView analysis){
        return analysesGroup.add(analysis);
    }

}
