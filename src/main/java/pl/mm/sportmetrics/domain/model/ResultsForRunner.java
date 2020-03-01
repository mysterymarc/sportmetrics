package pl.mm.sportmetrics.domain.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResultsForRunner {

    private int position;
    private String competitorName;
    private String competitorCity;
    private Time totalTime;
    private Time delayTime;
    private List<Result> segmentResults;
    private List<Result> cumulativeResults;
    private Long competitorId;
    private Long totalResultId;

    private ResultsForRunner(Builder builder) {
        position = builder.position;
        competitorName = builder.competitorName;
        competitorCity = builder.competitorCity;
        totalTime = builder.totalTime;
        delayTime = builder.delayTime;
        segmentResults = builder.segmentResults;
        cumulativeResults = builder.cumulativeResults;
        competitorId = builder.competitorId;
        totalResultId = builder.totalResultId;
    }

    public int getPosition() {
        return position;
    }

    public String getCompetitorName() {
        return competitorName;
    }

    public String getCompetitorCity() {
        return competitorCity;
    }

    public Time getTotalTime() {
        return totalTime;
    }

    public Time getDelayTime() {
        return delayTime;
    }

    public List<Result> getSegmentResults() {
        return segmentResults;
    }

    public List<Result> getCumulativeResults() {
        return cumulativeResults;
    }

    public Long getCompetitorId() {
        return competitorId;
    }

    public Long getTotalResultId() {
        return totalResultId;
    }

    public static class Builder {

        private int position;
        private String competitorName;
        private String competitorCity;
        private Time totalTime;
        private Time delayTime;
        private List<Result> segmentResults = new ArrayList<>();
        private List<Result> cumulativeResults = new ArrayList<>();
        private Long competitorId;
        private Long totalResultId;

        public Builder withName(String competitorName) {
            this.competitorName = competitorName;
            return this;
        }

        public Builder fromCity(String competitorCity) {
            this.competitorCity = competitorCity;
            return this;
        }

        public Builder achievedPosition(int position) {
            this.position = position;
            return this;
        }

        public Builder reachedTotalTime(Time totalTime) {
            this.totalTime = totalTime;
            return this;
        }

        public Builder reachedTotalTime(String totalTime) {
            return reachedTotalTime(Time.valueOf(totalTime));
        }

        public Builder withDelayToWinner(Time delayTime) {
            this.delayTime = delayTime;
            return this;
        }

        public Builder withDelayToWinner(String delayTime) {
            return withDelayToWinner(Time.valueOf(delayTime));
        }

        public Builder withSegmentResults(List<Result> segmentResults) {
            this.segmentResults = segmentResults;
            return this;
        }

        public Builder withCumulativeResults(List<Result> cumulativeResults) {
            this.cumulativeResults = cumulativeResults;
            return this;
        }

        public Builder competitorSignedById(Long id) {
            this.competitorId = id;
            return this;
        }

        public Builder totalResultSignedById(Long id) {
            this.totalResultId = id;
            return this;
        }

        public ResultsForRunner build() {
            return new ResultsForRunner(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultsForRunner that = (ResultsForRunner) o;
        return position == that.position &&
                Objects.equals(competitorName, that.competitorName) &&
                Objects.equals(competitorCity, that.competitorCity) &&
                Objects.equals(totalTime, that.totalTime) &&
                Objects.equals(delayTime, that.delayTime) &&
                Objects.equals(segmentResults, that.segmentResults) &&
                Objects.equals(cumulativeResults, that.cumulativeResults) &&
                Objects.equals(competitorId, that.competitorId) &&
                Objects.equals(totalResultId, that.totalResultId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, competitorName, competitorCity, totalTime, delayTime, segmentResults, cumulativeResults, competitorId, totalResultId);
    }

    @Override
    public String toString() {
        return "ResultsForRunner{" +
                "position=" + position +
                ", competitorName='" + competitorName + '\'' +
                ", competitorCity='" + competitorCity + '\'' +
                ", totalTime=" + totalTime +
                ", delayTime=" + delayTime +
                ", segmentResults=" + segmentResults +
                ", cumulativeResults=" + cumulativeResults +
                ", competitorId=" + competitorId +
                ", totalResultId=" + totalResultId +
                '}';
    }
}
