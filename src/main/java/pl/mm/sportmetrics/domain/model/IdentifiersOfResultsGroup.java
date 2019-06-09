package pl.mm.sportmetrics.domain.model;

import java.util.ArrayList;
import java.util.List;

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


}
