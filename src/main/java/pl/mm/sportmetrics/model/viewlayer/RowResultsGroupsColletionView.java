package pl.mm.sportmetrics.model.viewlayer;

import java.util.ArrayList;
import java.util.List;

public class RowResultsGroupsColletionView {

    private List<RowResultsGroupView> rowsGroupsView = new ArrayList<RowResultsGroupView>();

    public List<RowResultsGroupView> getRowsGroupsView() {
        return rowsGroupsView;
    }

    public void setRowsGroupsView(List<RowResultsGroupView> rowsGroupsView) {
        this.rowsGroupsView = rowsGroupsView;
    }

    public boolean add(RowResultsGroupView item){
        return rowsGroupsView.add(item);
    }
}
