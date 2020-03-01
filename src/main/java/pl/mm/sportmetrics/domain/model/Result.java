package pl.mm.sportmetrics.domain.model;

import java.sql.Time;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return position == result.position &&
                Objects.equals(time, result.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, position);
    }

    @Override
    public String toString() {
        return "Result{" +
                "time=" + time +
                ", position=" + position +
                '}';
    }
}
