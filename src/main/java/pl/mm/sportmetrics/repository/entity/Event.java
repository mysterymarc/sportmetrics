package pl.mm.sportmetrics.repository.entity;

import pl.mm.sportmetrics.repository.entity.*;
import pl.mm.sportmetrics.model.inputfile.EventDataCollection;
import pl.mm.sportmetrics.model.inputfile.SingleResultSet;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Event {

    public Competition competition = new Competition();
    public List<Competitor> competitors = new ArrayList<>();
    public List<Segment> segments = new ArrayList<>();
    public List<TotalResult> totalResults = new ArrayList<>();;
    public List<PartialResult> partialResults = new ArrayList<>();


}
