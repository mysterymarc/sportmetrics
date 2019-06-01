package pl.mm.sportmetrics.model.viewobject;

import pl.mm.sportmetrics.model.repo.CompetitionsCollection;

public class EventsPageDTO {

    private CompetitionsCollection events;

    public CompetitionsCollection getEvents() {
        return events;
    }

    public void setEvents(CompetitionsCollection events) {
        this.events = events;
    }
}
