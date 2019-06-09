package pl.mm.sportmetrics.services;

import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.dto.viewlayer.EventsPageDTO;
import pl.mm.sportmetrics.repository.CompetitionRepository;

@Service
public class EventsService {

    private CompetitionRepository competitionRepository;

    public EventsService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public EventsPageDTO getDataForView(){
        EventsPageDTO events = new EventsPageDTO();
        events.setEvents(competitionRepository.findAllCompetitions());
        return events;
    }
}
