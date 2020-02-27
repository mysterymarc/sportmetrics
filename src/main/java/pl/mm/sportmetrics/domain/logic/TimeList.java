package pl.mm.sportmetrics.domain.logic;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class TimeList implements Iterable<Time> {

    private final List<Time> row;

    public TimeList(){
        this.row = new ArrayList<>();
    }

    public TimeList(List<Time> row){
        this.row = row;
    }

    @Override
    public Iterator<Time> iterator() {
        return row.iterator();
    }

    public Time getElement(int i){
        return row.get(i);
    }

    public List<Time> getRow(){
        return row;
    }

    public void addElement(Time element){
        row.add(element);
    }

    public int size(){
        return row.size();
    }

    public boolean add(Time time){
        return row.add(time);
    }

    public boolean add(String time) {
        return row.add(Time.valueOf(time));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeList times = (TimeList) o;
        return Objects.equals(row, times.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row);
    }
}
