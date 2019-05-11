package pl.mm.sportmetrics.model.repo;

import java.sql.Time;

public class SingleStatistic {
    Time value;
    String description;

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
}
