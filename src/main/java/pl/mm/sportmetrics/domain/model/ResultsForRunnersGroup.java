package pl.mm.sportmetrics.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultsForRunnersGroup implements Iterable<ResultsForRunner> {

    public List<ResultsForRunner> resultsForRunners = new ArrayList<ResultsForRunner>();


    public boolean add(ResultsForRunner row){
        return resultsForRunners.add(row);
    }

    @Override
    public Iterator<ResultsForRunner> iterator() {
        return resultsForRunners.iterator();
    }

}
