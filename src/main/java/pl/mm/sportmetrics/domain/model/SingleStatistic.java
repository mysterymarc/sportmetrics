package pl.mm.sportmetrics.domain.model;

import java.sql.Time;

public class SingleStatistic {
    private Time value;
    private String description;

    public SingleStatistic(Time time){
        this.value = time;
    }

    public SingleStatistic(Time time, String description){
        this.value = time;
        this.description = description;
    }

    public Time getValue() {
        return value;
    }

    public void setValue(Time value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void evaluateDescriptionWithWinLossByComparison(SingleStatistic comparedTo){
        if (this.getValue().equals(Time.valueOf("00:00:00")) && comparedTo.getValue().equals(Time.valueOf("00:00:00"))) {
            description="draw";
        } else if (this.getValue().equals(Time.valueOf("00:00:00"))) {
            description="loss";
        } else if (comparedTo.getValue().equals(Time.valueOf("00:00:00"))) {
            description="win";
        } else if (this.getValue().getTime() > comparedTo.getValue().getTime()) {
            description="loss";
        } else if (this.getValue().getTime() < comparedTo.getValue().getTime()) {
            description="win";
        } else if (this.getValue().getTime() == comparedTo.getValue().getTime()) {
            description="draw";
        }
    }

}
