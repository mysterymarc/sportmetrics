package pl.mm.sportmetrics.model.inputlayer;

import java.util.List;

public class SingleResultSet {

    public int position;
    public String competitor;
    public String city;
    public String total;
    public String delay;
    public List<AtomResult> segmentResults;
    public List<AtomResult> cumulativeResults;
}
