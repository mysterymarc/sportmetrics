package pl.mm.sportmetrics.dto.viewlayer;

import pl.mm.sportmetrics.domain.model.CompetitionsCollection;

public class EventsPageDTO {

    private CompetitionsCollection events;

    public CompetitionsCollection getEvents() {
        return events;
    }

    public void setEvents(CompetitionsCollection events) {
        this.events = events;
    }
}
