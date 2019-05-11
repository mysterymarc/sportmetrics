package pl.mm.sportmetrics.model.viewobject;

import java.util.ArrayList;
import java.util.List;

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
}
