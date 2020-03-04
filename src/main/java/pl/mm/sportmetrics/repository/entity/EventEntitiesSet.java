package pl.mm.sportmetrics.repository.entity;

import pl.mm.sportmetrics.dto.inputlayer.EventDTO;
import pl.mm.sportmetrics.dto.inputlayer.SingleResultSet;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventEntitiesSet {

    public final CompetitionEntity competition = new CompetitionEntity();
    public final List<CompetitorEntity> competitors = new ArrayList<>();
    public final List<SegmentEntity> segments = new ArrayList<>();
    public final List<TotalResultEntity> totalResults = new ArrayList<>();
    public final List<PartialResultEntity> partialResults = new ArrayList<>();

    public EventEntitiesSet(EventDTO eventDTO) {
        mapCompetition(eventDTO);
        mapSegments(eventDTO);
        mapResults(eventDTO);
    }

    private Time parseTime(String time) {

        time = time.replace(".", ":")
                .replace("+", "");

        if (time.contains("-")) {
            time = "00:00:00";
        }

        int length = time.length();

        if (length < 4) {
            time = "00:00:00";
        }
        if (length == 4) {
            time = "00:0" + time;
        }
        if (length == 5) {
            time = "00:" + time;
        }
        if (length == 7) {
            time = "0" + time;
        }
        return (Time.valueOf(time));
    }

    private void mapCompetition(EventDTO eventDTO){
        competition.name = eventDTO.name;
    }

    private void mapSegments(EventDTO eventDTO){
        for (int i = 0; i < eventDTO.segments.size(); i++) {
            SegmentEntity tmpSeg = new SegmentEntity();
            tmpSeg.name = eventDTO.segments.get(i);
            tmpSeg.competition = competition;
            tmpSeg.orderNumber = i;
            segments.add(tmpSeg);
        }
    }

    private void mapResults(EventDTO eventDTO){
        for (SingleResultSet set : eventDTO.results) {
            CompetitorEntity tmpComp = new CompetitorEntity();
            tmpComp.name = set.competitor;
            tmpComp.city = set.city;
            competitors.add(tmpComp);

            TotalResultEntity tmpTotal = new TotalResultEntity();
            tmpTotal.competition = competition;
            tmpTotal.competitor = tmpComp;
            tmpTotal.position = set.position;
            tmpTotal.totalTime = parseTime(set.total);
            tmpTotal.delayTime = parseTime(set.delay);
            totalResults.add(tmpTotal);

            for (int i = 0; i < segments.size(); i++) {
                PartialResultEntity tmpPartial = new PartialResultEntity();
                tmpPartial.segment = segments.get(i);
                tmpPartial.segmentPosition = set.segmentResults.get(i).position;
                tmpPartial.segmentTime = parseTime(set.segmentResults.get(i).time);
                tmpPartial.cumulativePosition = set.cumulativeResults.get(i).position;
                tmpPartial.cumulativeTime = parseTime(set.cumulativeResults.get(i).time);
                tmpPartial.totalResult = tmpTotal;
                partialResults.add(tmpPartial);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEntitiesSet that = (EventEntitiesSet) o;
        return Objects.equals(competition, that.competition) &&
                Objects.equals(competitors, that.competitors) &&
                Objects.equals(segments, that.segments) &&
                Objects.equals(totalResults, that.totalResults) &&
                Objects.equals(partialResults, that.partialResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competition, competitors, segments, totalResults, partialResults);
    }

    @Override
    public String toString() {
        return "EventEntitiesSet{" +
                "competition=" + competition +
                ", competitors=" + competitors +
                ", segments=" + segments +
                ", totalResults=" + totalResults +
                ", partialResults=" + partialResults +
                '}';
    }
}
