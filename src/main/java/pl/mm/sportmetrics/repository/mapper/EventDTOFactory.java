package pl.mm.sportmetrics.repository.mapper;

import pl.mm.sportmetrics.model.inputlayer.Event;
import pl.mm.sportmetrics.model.inputlayer.SingleResultSet;
import pl.mm.sportmetrics.repository.entity.*;

import java.sql.Time;
// TODO: czy to ma byc factory, moze to raczej jest mapper?
public class EventDTOFactory {

    public EventDTO getEvent(Event event){
        EventDTO createdEvent = new EventDTO();     //TODO: czy tu moze byc new?
// TODO: rozbic na kilka metod, po co wszystko w jednej:
        createdEvent.competition.name = event.name;

        for (int i = 0; i < event.segments.size(); i++) {
            SegmentEntity tmpSeg = new SegmentEntity();
            tmpSeg.name = event.segments.get(i);
            tmpSeg.competition = createdEvent.competition;
            tmpSeg.orderNumber = i;
            createdEvent.segments.add(tmpSeg);
        }

        for (SingleResultSet set : event.results) {
            CompetitorEntity tmpComp = new CompetitorEntity();
            tmpComp.name = set.competitor;
            tmpComp.city = set.city;
            createdEvent.competitors.add(tmpComp);

            TotalResultEntity tmpTotal = new TotalResultEntity();
            tmpTotal.competition = createdEvent.competition;
            tmpTotal.competitor = tmpComp;
            tmpTotal.position = set.position;
            tmpTotal.totalTime = parseTime(set.total);
            tmpTotal.delayTime = parseTime(set.delay);
            createdEvent.totalResults.add(tmpTotal);


            for (int i = 0; i < createdEvent.segments.size(); i++) {
                PartialResultEntity tmpPartial = new PartialResultEntity();
                tmpPartial.segment = createdEvent.segments.get(i);
                tmpPartial.segmentPosition = set.segmentResults.get(i).position;
                tmpPartial.segmentTime = parseTime(set.segmentResults.get(i).time);
                tmpPartial.cumulativePosition = set.cumulativeResults.get(i).position;
                tmpPartial.cumulativeTime = parseTime(set.cumulativeResults.get(i).time);
                tmpPartial.totalResult = tmpTotal;
                createdEvent.partialResults.add(tmpPartial);
            }

        }

        return createdEvent;
    }

    // TODO: to wygląda dość mocno hackowato, może się nie dać inaczej ale pomyśl coś w deseń https://stackoverflow.com/questions/6403851/parsing-time-strings-like-1h-30min
    private Time parseTime(String time) {

        time = time.replace(".", ":")
                .replace("+", "")
        ;

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
}
