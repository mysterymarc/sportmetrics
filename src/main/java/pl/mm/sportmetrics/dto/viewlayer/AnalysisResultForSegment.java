package pl.mm.sportmetrics.dto.viewlayer;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisResultForSegment that = (AnalysisResultForSegment) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(valueAttribute, that.valueAttribute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, valueAttribute);
    }

    @Override
    public String toString() {
        return "AnalysisResultForSegment{" +
                "value='" + value + '\'' +
                ", valueAttribute='" + valueAttribute + '\'' +
                '}';
    }
}
