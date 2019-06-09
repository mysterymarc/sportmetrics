package pl.mm.sportmetrics.dto.viewlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RowResultsGroupsColletionView {

    private List<RowResultsGroupView> rowsGroupsView = new ArrayList<RowResultsGroupView>();

    public RowResultsGroupsColletionView() {
    }

    public RowResultsGroupsColletionView(List<RowResultsGroupView> rowsGroupsView) {
        this.rowsGroupsView = rowsGroupsView;
    }

    public List<RowResultsGroupView> getRowsGroupsView() {
        return rowsGroupsView;
    }

    public void setRowsGroupsView(List<RowResultsGroupView> rowsGroupsView) {
        this.rowsGroupsView = rowsGroupsView;
    }

    public boolean add(RowResultsGroupView item){
        return rowsGroupsView.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RowResultsGroupsColletionView that = (RowResultsGroupsColletionView) o;
        return Objects.equals(rowsGroupsView, that.rowsGroupsView);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowsGroupsView);
    }

    @Override
    public String toString() {
        return "RowResultsGroupsColletionView{" +
                "rowsGroupsView=" + rowsGroupsView +
                '}';
    }
}
