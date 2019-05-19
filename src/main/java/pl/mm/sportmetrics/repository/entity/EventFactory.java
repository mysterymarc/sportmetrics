package pl.mm.sportmetrics.repository.entity;

import pl.mm.sportmetrics.model.inputfile.EventDataCollection;
import pl.mm.sportmetrics.model.inputfile.SingleResultSet;

import java.sql.Time;

public class EventFactory {

    public Event getEvent(EventDataCollection eventDataCollection){
        Event createdEvent = new Event();

        createdEvent.competition.name = eventDataCollection.name;

        for (int i = 0; i < eventDataCollection.segments.size();i++) {
            Segment tmpSeg = new Segment();
            tmpSeg.name = eventDataCollection.segments.get(i);
            tmpSeg.competition = createdEvent.competition;
            tmpSeg.orderNumber = i;
            createdEvent.segments.add(tmpSeg);
        }

        for (SingleResultSet set : eventDataCollection.results) {
            Competitor tmpComp = new Competitor();
            tmpComp.name = set.competitor;
            tmpComp.city = set.city;
            createdEvent.competitors.add(tmpComp);

            TotalResult tmpTotal = new TotalResult();
            tmpTotal.competition = createdEvent.competition;
            tmpTotal.competitor = tmpComp;
            tmpTotal.position = set.position;
            tmpTotal.totalTime = parseTime(set.total);
            tmpTotal.delayTime = parseTime(set.delay);
            createdEvent.totalResults.add(tmpTotal);


            for (int i = 0; i < createdEvent.segments.size(); i++) {
                PartialResult tmpPartial = new PartialResult();
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
