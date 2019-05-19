package pl.mm.sportmetrics.model.repo;

import pl.mm.sportmetrics.repository.entity.Segment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Segments implements Iterable<String>{

    List<String> segments = new ArrayList<String>();

    public Segments(){

    }

    public Segments(List<String> segments){
        this.segments = segments;
    }

    @Override
    public Iterator<String> iterator() {
        return segments.iterator();
    }

    public void add(String segment){
        segments.add(segment);
    }

    public List<String> getSegments() {
        return segments;
    }

    public void setSegments(List<String> segments) {
        this.segments = segments;
    }
}
