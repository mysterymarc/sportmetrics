package pl.mm.sportmetrics.mapper;

public class DAOToViewOfResultsMatrixMapper {
/*
    private List<RowResultView> resultRows;

    public DAOToViewOfResultsMatrixMapper() {
        this.resultRows = new ArrayList<>();
    }

    public List<RowResultView> getResultsMatrix() {
        return resultRows;
    }

    public void doMapping(List<Competitor> competitors, List<TotalResult> totalResults, List<List<PartialResult>> partialResultsSet){
        for(int i = 0; i < competitors.size(); i++){
            RowResultView row = new RowResultView();
            row.setCompetitorId(competitors.get(i).id);
            row.setCompetitorName(competitors.get(i).name);
            row.setCompetitorCity(competitors.get(i).city);
            row.setTotalResultId(totalResults.get(i).id);
            row.setDelayTime(mapTimeToViewForm(totalResults.get(i).delayTime));
            row.setTotalTime(mapTimeToViewForm(totalResults.get(i).totalTime));
            row.setPosition(mapPositionToViewForm(totalResults.get(i).position));
            List<SingleResultView> segmentResults = new ArrayList<>();
            List<SingleResultView> cumulativeResults = new ArrayList<>();
            for(PartialResult res : partialResultsSet.get(i)){
                SingleResultView segRes = new SingleResultView();
                segRes.position = mapPositionToViewForm(res.segmentPosition);
                segRes.time = mapTimeToViewForm(res.segmentTime);
                segmentResults.add(segRes);
                SingleResultView cumRes = new SingleResultView();
                cumRes.position = mapPositionToViewForm(res.cumulativePosition);
                cumRes.time = mapTimeToViewForm(res.cumulativeTime);
                cumulativeResults.add(cumRes);
            }
            row.setSegmentResults(segmentResults);
            row.setCumulativeResults(cumulativeResults);
            resultRows.add(row);
        }
    }


    private String mapTimeToViewForm(Time sourceTime){
        String time = sourceTime.toString();
        if(time.equals("00:00:00")){
            time = "";
        }
        if(time.startsWith("00:") && time.length() == 8){
            time = time.substring(3);
        }
        return time;
    }

    private String mapPositionToViewForm(int position){
        if(position==-1){return "x";}
        return String.valueOf(position);
    }
*/
}
