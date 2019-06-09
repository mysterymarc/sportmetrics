package pl.mm.sportmetrics.domain.model;

import java.sql.Time;

public class SingleStatistic {
    private Time value;
    private Score description;

    public SingleStatistic(Time time){
        this.value = time;
    }

    public SingleStatistic(String time){
        this.value = Time.valueOf(time);
    }

    public SingleStatistic(Time time, Score description){
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
        return description.toString();
    }

    public void evaluateDescriptionWithWinLossByComparison(SingleStatistic comparedTo){
        if (this.getValue().equals(Time.valueOf("00:00:00")) && comparedTo.getValue().equals(Time.valueOf("00:00:00"))) {
            description=Score.DRAW;
        } else if (this.getValue().equals(Time.valueOf("00:00:00"))) {
            description=Score.LOSS;
        } else if (comparedTo.getValue().equals(Time.valueOf("00:00:00"))) {
            description=Score.WIN;
        } else if (this.getValue().getTime() > comparedTo.getValue().getTime()) {
            description=Score.LOSS;
        } else if (this.getValue().getTime() < comparedTo.getValue().getTime()) {
            description=Score.WIN;
        } else if (this.getValue().getTime() == comparedTo.getValue().getTime()) {
            description=Score.DRAW;
        }
    }

}
