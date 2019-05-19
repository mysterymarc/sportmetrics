package pl.mm.sportmetrics.mapper;

import pl.mm.sportmetrics.repository.entity.*;
import pl.mm.sportmetrics.model.inputfile.CompetitionResultSet;
import pl.mm.sportmetrics.model.inputfile.SingleResultSet;
import pl.mm.sportmetrics.model.repo.Segments;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CompetitionResultSetToDAOMapper {

    public Competition competition;
    public List<Competitor> competitors;
    public List<Segment> segments;
    public List<TotalResult> totalResults;
    public List<PartialResult> partialResults;

    public CompetitionResultSetToDAOMapper() {
        competition = new Competition();
        competitors = new ArrayList<>();
        segments = new ArrayList<>();
        totalResults = new ArrayList<>();
        partialResults = new ArrayList<>();
    }


    public void doMapping(CompetitionResultSet resultSet) {

        competition.name = resultSet.name;

        for (int i = 0; i < resultSet.segments.size();i++) {
            Segment tmpSeg = new Segment();
            tmpSeg.name = resultSet.segments.get(i);
            tmpSeg.competition = competition;
            tmpSeg.orderNumber = i;
            segments.add(tmpSeg);
        }

        for (SingleResultSet set : resultSet.results) {
            Competitor tmpComp = new Competitor();
            tmpComp.name = set.competitor;
            tmpComp.city = set.city;
            competitors.add(tmpComp);

            TotalResult tmpTotal = new TotalResult();
            tmpTotal.competition = competition;
            tmpTotal.competitor = tmpComp;
            tmpTotal.position = set.position;
            tmpTotal.totalTime = parseTime(set.total);
            tmpTotal.delayTime = parseTime(set.delay);
            totalResults.add(tmpTotal);


            for (int i = 0; i < segments.size(); i++) {
                PartialResult tmpPartial = new PartialResult();
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



    // TODO: to wygląda dość mocno hackowato, może się nie dać inaczej ale pomyśl coś w deseń https://stackoverflow.com/questions/6403851/parsing-time-strings-like-1h-30min
    private Time parseTime(String time){

        time = time.replace(".",":")
                .replace("+","")
        ;

        if(time.contains("-")){
            time = "00:00:00";
        }

        int length = time.length();

        if(length < 4) {
            time = "00:00:00";
        }
        if(length == 4) {
            time = "00:0" + time;
        }
        if(length == 5) {
            time = "00:" + time;
        }
        if(length == 7) {
            time = "0" + time;
        }
        return (Time.valueOf(time));
    }
}
