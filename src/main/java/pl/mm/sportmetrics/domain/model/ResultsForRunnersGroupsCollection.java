package pl.mm.sportmetrics.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultsForRunnersGroupsCollection implements Iterable<ResultsForRunnersGroup> {

    private final List<ResultsForRunnersGroup> scoreRowsGroups = new ArrayList<>();

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
