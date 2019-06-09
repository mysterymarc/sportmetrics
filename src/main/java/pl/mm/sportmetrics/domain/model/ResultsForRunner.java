package pl.mm.sportmetrics.domain.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ResultsForRunner {

    private int position;
    private String competitorName;
    private String competitorCity;
    private Time totalTime;
    private Time delayTime;
    private List<Result> segmentResults = new ArrayList<Result>();
    private List<Result> cumulativeResults = new ArrayList<Result>();
    private Long competitorId;
    private Long totalResultId;

    public ResultsForRunner(){

    }

    public ResultsForRunner(int position, String competitorName, String competitorCity, Time totalTime, Time delayTime, List<Result> segmentResults, List<Result> cumulativeResults, Long competitorId, Long totalResultId) {
        this.position = position;
        this.competitorName = competitorName;
        this.competitorCity = competitorCity;
        this.totalTime = totalTime;
        this.delayTime = delayTime;
        this.segmentResults = segmentResults;
        this.cumulativeResults = cumulativeResults;
        this.competitorId = competitorId;
        this.totalResultId = totalResultId;
    }

    public ResultsForRunner(int position, String competitorName, String competitorCity, String totalTime, String delayTime, List<Result> segmentResults, List<Result> cumulativeResults, Long competitorId, Long totalResultId) {
        this.position = position;
        this.competitorName = competitorName;
        this.competitorCity = competitorCity;
        this.totalTime = Time.valueOf(totalTime);
        this.delayTime = Time.valueOf(delayTime);
        this.segmentResults = segmentResults;
        this.cumulativeResults = cumulativeResults;
        this.competitorId = competitorId;
        this.totalResultId = totalResultId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCompetitorName() {
        return competitorName;
    }

    public void setCompetitorName(String competitorName) {
        this.competitorName = competitorName;
    }

    public String getCompetitorCity() {
        return competitorCity;
    }

    public void setCompetitorCity(String competitorCity) {
        this.competitorCity = competitorCity;
    }

    public Time getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Time totalTime) {
        this.totalTime = totalTime;
    }

    public Time getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Time delayTime) {
        this.delayTime = delayTime;
    }

    public List<Result> getSegmentResults() {
        return segmentResults;
    }

    public void setSegmentResults(List<Result> segmentResults) {
        this.segmentResults = segmentResults;
    }

    public List<Result> getCumulativeResults() {
        return cumulativeResults;
    }

    public void setCumulativeResults(List<Result> cumulativeResults) {
        this.cumulativeResults = cumulativeResults;
    }

    public Long getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Long competitorId) {
        this.competitorId = competitorId;
    }

    public Long getTotalResultId() {
        return totalResultId;
    }

    public void setTotalResultId(Long totalResultId) {
        this.totalResultId = totalResultId;
    }

    public void addSegmentResult(Result result){
        segmentResults.add(result);
    }

    public void addAllSegmentResults(List<Result> results){
        segmentResults.addAll(results);
    }

    public void addCumulativeResult(Result result){
        cumulativeResults.add(result);
    }

    public void addAllCumulativeResults(List<Result> results){
        cumulativeResults.addAll(results);
    }




}
