package pl.mm.sportmetrics.model.viewlayer;

public class SingleResultView {
    public String time;
    public String position;

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
}
