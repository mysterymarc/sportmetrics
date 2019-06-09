package pl.mm.sportmetrics.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segments strings = (Segments) o;
        return Objects.equals(segments, strings.segments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segments);
    }

    @Override
    public String toString() {
        return "Segments{" +
                "segments=" + segments +
                '}';
    }
}
