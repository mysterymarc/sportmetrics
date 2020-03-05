package pl.mm.sportmetrics.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IdentifiersOfResultsGroup {

    private final List<Long> identifiers = new ArrayList<>();

    public boolean add(Long identifier){
        return identifiers.add(identifier);
    }

    public boolean addAll(List<Long> identifiers){
        return this.identifiers.addAll(identifiers);
    }

    public List<Long> getIdentifiers() {
        return identifiers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifiersOfResultsGroup that = (IdentifiersOfResultsGroup) o;
        return Objects.equals(identifiers, that.identifiers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiers);
    }
}
