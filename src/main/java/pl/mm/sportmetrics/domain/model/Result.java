package pl.mm.sportmetrics.domain.model;

import java.sql.Time;

public class Result {

    private Time time;
    private int position;

    public Result(Time time, int position) {
        this.time = time;
        this.position = position;
    }

    public Result(String time, int position) {
        this.time = Time.valueOf(time);
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
