package pl.mm.sportmetrics.model.businesslayer;

import java.sql.Time;

public class SingleStatistic {
    private Time value;
    private String description;

    public Time getValue() {
        return value;
    }

    public void setValue(Time value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void evaluateDescriptionWithWinLossByComparison(SingleStatistic comparedTo){
        if (this.getValue().equals(Time.valueOf("00:00:00")) && comparedTo.getValue().equals(Time.valueOf("00:00:00"))) {
            this.setDescription("draw");
        } else if (this.getValue().equals(Time.valueOf("00:00:00"))) {
            this.setDescription("loss");
        } else if (comparedTo.getValue().equals(Time.valueOf("00:00:00"))) {
            this.setDescription("win");
        } else if (this.getValue().getTime() > comparedTo.getValue().getTime()) {
            this.setDescription("loss");
        } else if (this.getValue().getTime() < comparedTo.getValue().getTime()) {
            this.setDescription("win");
        } else if (this.getValue().getTime() == comparedTo.getValue().getTime()) {
            this.setDescription("draw");
        } else {
            this.setDescription("error");
        }
    }

}
