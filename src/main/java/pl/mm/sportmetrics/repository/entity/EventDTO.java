package pl.mm.sportmetrics.repository.entity;

import java.util.ArrayList;
import java.util.List;
//TODO:x Czy to moze sie nazywac DTO, czy powinno jakos inaczej - na pewno nie DTO, jak juz cos to tamten Event powinien sie nazywac DTO

//TODO;x po refactorze Respository może to wyleciec calkowicie, bo chyba bedzie niepottrzebne juz, jest to wogole diwne.


//TODO:x Czy to ma byc w tym pakiecie - w sumie to jest zbior wsyztskich entity, ale czy to tu pasuje - NIE




public class EventDTO {

    public CompetitionEntity competition = new CompetitionEntity();
    public List<CompetitorEntity> competitors = new ArrayList<>();
    public List<SegmentEntity> segments = new ArrayList<>();
    public List<TotalResultEntity> totalResults = new ArrayList<>();;
    public List<PartialResultEntity> partialResults = new ArrayList<>();


}
