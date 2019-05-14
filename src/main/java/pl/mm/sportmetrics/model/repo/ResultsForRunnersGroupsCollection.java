package pl.mm.sportmetrics.model.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultsForRunnersGroupsCollection implements Iterable<ResultsForRunnersGroup> {

    private List<ResultsForRunnersGroup> scoreRowsGroups = new ArrayList<ResultsForRunnersGroup>();

    public boolean add(ResultsForRunnersGroup rowsGroup){
        return scoreRowsGroups.add(rowsGroup);
    }

    @Override
    public Iterator<ResultsForRunnersGroup> iterator() {
        return scoreRowsGroups.iterator();
    }

    ResultsForRunnersGroup getGroup(int i){
        return scoreRowsGroups.get(i);
    }
}
