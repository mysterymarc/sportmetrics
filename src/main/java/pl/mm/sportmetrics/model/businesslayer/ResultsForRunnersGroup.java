package pl.mm.sportmetrics.model.businesslayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultsForRunnersGroup implements Iterable<ResultsForRunner> {

    private List<ResultsForRunner> resultsForRunners = new ArrayList<ResultsForRunner>();


    public boolean add(ResultsForRunner row){
        return resultsForRunners.add(row);
    }

    @Override
    public Iterator<ResultsForRunner> iterator() {
        return resultsForRunners.iterator();
    }

    public ResultsForRunner getRow(int i){
        return resultsForRunners.get(i);
    }

    public List<Result> getAllCompetitorsSingleSegmentScores(int segmentNumber){
        List<Result> resultList = new ArrayList<>();
        for(ResultsForRunner row : resultsForRunners){
            resultList.add(row.getSegmentResults().get(segmentNumber));
        }
        return resultList;
    }

    public int getRowsNumber(){
        return resultsForRunners.size();
    }

    public int getSegmentsNumber(){
        return resultsForRunners.get(0).getSegmentResults().size();
    }
}
