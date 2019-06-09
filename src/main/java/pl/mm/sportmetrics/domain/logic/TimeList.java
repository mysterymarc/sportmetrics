package pl.mm.sportmetrics.domain.logic;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TimeList implements Iterable<Time> {

    private List<Time> row;

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

}
