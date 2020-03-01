package pl.mm.sportmetrics.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ResultsForRunnersGroup implements Iterable<ResultsForRunner> {

    public final List<ResultsForRunner> resultsForRunners = new ArrayList<>();


    public boolean add(ResultsForRunner row){
        return resultsForRunners.add(row);
    }

    @Override
    public Iterator<ResultsForRunner> iterator() {
        return resultsForRunners.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultsForRunnersGroup results = (ResultsForRunnersGroup) o;
        return Objects.equals(resultsForRunners, results.resultsForRunners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultsForRunners);
    }

    @Override
    public String toString() {
        return "ResultsForRunnersGroup{" +
                "resultsForRunners=" + resultsForRunners +
                '}';
    }
}
