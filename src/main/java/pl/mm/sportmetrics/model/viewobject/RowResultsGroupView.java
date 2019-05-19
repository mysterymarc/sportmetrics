package pl.mm.sportmetrics.model.viewobject;
import java.util.ArrayList;
import java.util.List;

public class RowResultsGroupView {

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
}
