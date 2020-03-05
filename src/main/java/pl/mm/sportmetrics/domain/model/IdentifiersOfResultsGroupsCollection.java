package pl.mm.sportmetrics.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class IdentifiersOfResultsGroupsCollection implements Iterable<IdentifiersOfResultsGroup> {

    private final List<IdentifiersOfResultsGroup> identifiersGroups = new ArrayList<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifiersOfResultsGroupsCollection that = (IdentifiersOfResultsGroupsCollection) o;
        return Objects.equals(identifiersGroups, that.identifiersGroups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiersGroups);
    }
}
