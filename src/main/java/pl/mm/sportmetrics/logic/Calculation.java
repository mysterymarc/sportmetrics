package pl.mm.sportmetrics.logic;

import pl.mm.sportmetrics.model.businesslayer.SegmentsStatistic;

public interface Calculation {

    public TimeList getStatistic(TimeMatrix inputData);
    public String getStatisticName();
}
