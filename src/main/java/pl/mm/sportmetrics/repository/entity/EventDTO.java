package pl.mm.sportmetrics.repository.entity;

import pl.mm.sportmetrics.repository.entity.*;

import java.util.ArrayList;
import java.util.List;
//TODO: Czy to moze sie nazywac DTO, czy powinno jakos inaczej
//TODO: Czy to ma byc w tym pakiecie - w sumie to jest zbior wsyztskich entity, ale czy to tu pasuje
public class EventDTO {

    public CompetitionEntity competition = new CompetitionEntity();
    public List<CompetitorEntity> competitors = new ArrayList<>();
    public List<SegmentEntity> segments = new ArrayList<>();
    public List<TotalResultEntity> totalResults = new ArrayList<>();;
    public List<PartialResultEntity> partialResults = new ArrayList<>();


}
