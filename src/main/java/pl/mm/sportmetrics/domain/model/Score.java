package pl.mm.sportmetrics.domain.model;

public enum Score {
    WIN("win"),
    LOSS("loss"),
    DRAW("draw");

    private final String text;

    Score(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
