package pl.mm.sportmetrics.model.repo;

import java.sql.Time;

public class Result {

    Time time;
    int position;

    public Result(Time time, int position) {
        this.time = time;
        this.position = position;
    }

    public Result() {
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
