package pl.mm.sportmetrics.model.viewlayer;

import pl.mm.sportmetrics.model.businesslayer.CompetitionsCollection;

public class EventsPageDTO {

    private CompetitionsCollection events;

    public CompetitionsCollection getEvents() {
        return events;
    }

    public void setEvents(CompetitionsCollection events) {
        this.events = events;
    }
}
