package pl.mm.sportmetrics.dto.viewlayer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RowResultsGroupView {

    public RowResultsGroupView() {
    }

    public RowResultsGroupView(List<RowResultView> rowsView) {
        this.rowsView = rowsView;
    }

    private List<RowResultView> rowsView = new ArrayList<RowResultView>();

    public List<RowResultView> getRowsView() {
        return rowsView;
    }

    public void setRowsView(List<RowResultView> rowsView) {
        this.rowsView = rowsView;
    }

    public boolean add(RowResultView item){
        return rowsView.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RowResultsGroupView that = (RowResultsGroupView) o;
        return Objects.equals(rowsView, that.rowsView);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowsView);
    }

    @Override
    public String toString() {
        return "RowResultsGroupView{" +
                "rowsView=" + rowsView +
                '}';
    }
}
