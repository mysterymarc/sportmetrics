package pl.mm.sportmetrics.repository.entity;

import java.util.ArrayList;
import java.util.List;

public class Event {

    public CompetitionEntity competition = new CompetitionEntity();
    public List<CompetitorEntity> competitors = new ArrayList<>();
    public List<SegmentEntity> segments = new ArrayList<>();
    public List<TotalResultEntity> totalResults = new ArrayList<>();;
    public List<PartialResultEntity> partialResults = new ArrayList<>();


}
