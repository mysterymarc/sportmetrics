package pl.mm.sportmetrics.domain.logic;

public interface Calculation {

    public TimeList getStatistic(TimeMatrix inputData);
    public String getStatisticName();
}
