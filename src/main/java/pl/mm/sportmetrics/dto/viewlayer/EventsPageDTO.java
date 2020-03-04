package pl.mm.sportmetrics.dto.viewlayer;

import pl.mm.sportmetrics.domain.model.CompetitionsCollection;

import java.util.Objects;

public class EventsPageDTO {

    private CompetitionsCollection events;

    public CompetitionsCollection getEvents() {
        return events;
    }

    public void setEvents(CompetitionsCollection events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventsPageDTO that = (EventsPageDTO) o;
        return Objects.equals(events, that.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(events);
    }

    @Override
    public String toString() {
        return "EventsPageDTO{" +
                "events=" + events +
                '}';
    }
}
