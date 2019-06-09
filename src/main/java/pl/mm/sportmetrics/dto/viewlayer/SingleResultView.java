package pl.mm.sportmetrics.dto.viewlayer;

import java.util.Objects;

public class SingleResultView {
    private String time;
    private String position;

    public String getTime() {
        return time;
    }

    public String getPosition() {
        return position;
    }

    public SingleResultView(String time, String position) {
        this.time = time;
        this.position = position;
    }

    public SingleResultView() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleResultView that = (SingleResultView) o;
        return Objects.equals(time, that.time) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, position);
    }

    @Override
    public String toString() {
        return "SingleResultView{" +
                "time='" + time + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
