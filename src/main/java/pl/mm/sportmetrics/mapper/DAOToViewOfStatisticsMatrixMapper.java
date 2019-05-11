package pl.mm.sportmetrics.mapper;

public class DAOToViewOfStatisticsMatrixMapper {
/*
    private List<AnalysisResultRow> analysisRows;

    public DAOToViewOfStatisticsMatrixMapper() {
        this.analysisRows = new ArrayList<>();
    }

    public List<AnalysisResultRow> getResultsMatrix() {
        return analysisRows;
    }


    public void doMapping(List<List<Time>> statisticTimesSet, List<List<String>> statisticInterpretationsSet, String statisticTitle) {

        for (int j=0; j < statisticTimesSet.size(); j++) {
            AnalysisResultRow row = new AnalysisResultRow();
            row.setTitle(statisticTitle);
            List<AnalysisResultForSegment> segmentStats = new ArrayList<>();
            for (int i = 0; i < statisticTimesSet.get(j).size(); i++) {
                AnalysisResultForSegment segAnal = new AnalysisResultForSegment();
                segAnal.setValue(mapTimeToViewForm(statisticTimesSet.get(j).get(i)));
                segAnal.setValueAttribute(statisticInterpretationsSet.get(j).get(i));
                segmentStats.add(segAnal);
            }
            row.setSegmentResults(segmentStats);
            analysisRows.add(row);
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
*/}
