package pl.mm.sportmetrics.dto.viewlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RowResultView {

    private Long competitorId;
    private Long totalResultId;
    private String competitorName;
    private String competitorCity;
    private String position;
    private String totalTime;
    private String delayTime;
    private List<SingleResultView> segmentResults;
    private List<SingleResultView> cumulativeResults;

    public RowResultView(Long competitorId, Long totalResultId, String competitorName, String competitorCity, String position, String totalTime, String delayTime, List<SingleResultView> segmentResults, List<SingleResultView> cumulativeResults) {
        this.competitorId = competitorId;
        this.totalResultId = totalResultId;
        this.competitorName = competitorName;
        this.competitorCity = competitorCity;
        this.position = position;
        this.totalTime = totalTime;
        this.delayTime = delayTime;
        this.segmentResults = segmentResults;
        this.cumulativeResults = cumulativeResults;
    }

    public RowResultView() {
        this.segmentResults = new ArrayList<>();
    }

    public Long getCompetitorId() {
        return competitorId;
    }

    public Long getTotalResultId() {
        return totalResultId;
    }

    public String getCompetitorName() {
        return competitorName;
    }

    public String getCompetitorCity() {
        return competitorCity;
    }

    public String getPosition() {
        return position;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public String getDelayTime() {
        return delayTime;
    }

    public List<SingleResultView> getSegmentResults() {
        return segmentResults;
    }

    public List<SingleResultView> getCumulativeResults() {
        return cumulativeResults;
    }

    public void setCompetitorId(Long competitorId) {
        this.competitorId = competitorId;
    }

    public void setTotalResultId(Long totalResultId) {
        this.totalResultId = totalResultId;
    }

    public void setCompetitorName(String competitorName) {
        this.competitorName = competitorName;
    }

    public void setCompetitorCity(String competitorCity) {
        this.competitorCity = competitorCity;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public void setDelayTime(String delayTime) {
        this.delayTime = delayTime;
    }

    public void setSegmentResults(List<SingleResultView> segmentResults) {
        this.segmentResults = segmentResults;
    }

    public void setCumulativeResults(List<SingleResultView> cumulativeResults) {
        this.cumulativeResults = cumulativeResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RowResultView that = (RowResultView) o;
        return Objects.equals(competitorId, that.competitorId) &&
                Objects.equals(totalResultId, that.totalResultId) &&
                Objects.equals(competitorName, that.competitorName) &&
                Objects.equals(competitorCity, that.competitorCity) &&
                Objects.equals(position, that.position) &&
                Objects.equals(totalTime, that.totalTime) &&
                Objects.equals(delayTime, that.delayTime) &&
                Objects.equals(segmentResults, that.segmentResults) &&
                Objects.equals(cumulativeResults, that.cumulativeResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competitorId, totalResultId, competitorName, competitorCity, position, totalTime, delayTime, segmentResults, cumulativeResults);
    }

    @Override
    public String toString() {
        return "RowResultView{" +
                "competitorId=" + competitorId +
                ", totalResultId=" + totalResultId +
                ", competitorName='" + competitorName + '\'' +
                ", competitorCity='" + competitorCity + '\'' +
                ", position='" + position + '\'' +
                ", totalTime='" + totalTime + '\'' +
                ", delayTime='" + delayTime + '\'' +
                ", segmentResults=" + segmentResults +
                ", cumulativeResults=" + cumulativeResults +
                '}';
    }
}
