package pl.mm.sportmetrics.model.viewlayer;

public class AnalysisResultForSegment {

    private String value;
    private String valueAttribute;

    public AnalysisResultForSegment() {
    }

    public AnalysisResultForSegment(String value, String valueAttribute) {
        this.value = value;
        this.valueAttribute = valueAttribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueAttribute() {
        return valueAttribute;
    }

    public void setValueAttribute(String valueAttribute) {
        this.valueAttribute = valueAttribute;
    }
}
