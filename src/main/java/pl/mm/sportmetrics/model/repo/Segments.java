package pl.mm.sportmetrics.model.repo;

import org.springframework.context.annotation.Bean;
import pl.mm.sportmetrics.model.database.Segment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Segments implements Iterable<Segment>{

    List<Segment> segments = new ArrayList<Segment>();

    public Segments(){

    }

    public Segments(List<Segment> segments){
        this.segments = segments;
    }

    @Override
    public Iterator<Segment> iterator() {
        return segments.iterator();
    }

    public void add(Segment segment){
        segments.add(segment);
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }
}
