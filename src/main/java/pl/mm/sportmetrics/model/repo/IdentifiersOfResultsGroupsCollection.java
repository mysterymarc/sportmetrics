package pl.mm.sportmetrics.model.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IdentifiersOfResultsGroupsCollection implements Iterable<IdentifiersOfResultsGroup> {

    List<IdentifiersOfResultsGroup> identifiersGroups = new ArrayList<IdentifiersOfResultsGroup>();

    public boolean add(IdentifiersOfResultsGroup identifiersGroup){
        return identifiersGroups.add(identifiersGroup);
    }

    public boolean add(List<Long> identifiersGroup){
        IdentifiersOfResultsGroup newGroup = new IdentifiersOfResultsGroup();
        newGroup.addAll(identifiersGroup);
        return identifiersGroups.add(newGroup);
    }

    @Override
    public Iterator<IdentifiersOfResultsGroup> iterator() {
        return identifiersGroups.iterator();
    }
}
