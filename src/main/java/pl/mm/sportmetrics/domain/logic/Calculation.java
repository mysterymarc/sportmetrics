package pl.mm.sportmetrics.domain.logic;

public interface Calculation {

    TimeList getStatistic(TimeMatrix inputData);
    String getName();
}
