package pl.mm.sportmetrics.domain.logic;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TimeMatrix {

    private final List<TimeList> matrix = new ArrayList<>();

    public void addRow(List<Time> row){
        matrix.add(new TimeList(row));
    }

    public void addRow(TimeList row){
        matrix.add(row);
    }

    public Time getElement(int rowNum, int colNum){
        return matrix.get(rowNum).getElement(colNum);
    }

    private TimeList getColumn(int i){
        TimeList column = new TimeList();
        for(TimeList row : matrix){
            column.addElement(row.getElement(i));
        }
        return column;
    }

    public List<TimeList> getColumns(){
        List<TimeList> columns = new ArrayList<>();
        for(int i=0; i < matrix.get(0).size();i++){
            columns.add(getColumn(i));
        }
        return columns;
    }

    public int getRowsNumber(){
        return matrix.size();
    }
}
