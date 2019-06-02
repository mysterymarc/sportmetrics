package pl.mm.sportmetrics.model.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.model.viewobject.EventsPageDTO;
import pl.mm.sportmetrics.repository.Repository;

@Service
public class EventsService {

    private Repository repository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public EventsService(Repository repository){
        this.repository = repository;
    }

    public EventsPageDTO getDataForView(){
        EventsPageDTO events = new EventsPageDTO();
        events.setEvents(repository.findAllCompetitions());
        return events;
    }

}
