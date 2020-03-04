package pl.mm.sportmetrics.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.mm.sportmetrics.domain.model.Competition;
import pl.mm.sportmetrics.domain.model.CompetitionsCollection;
import pl.mm.sportmetrics.dto.viewlayer.EventsPageDTO;
import pl.mm.sportmetrics.repository.CompetitionRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventsServiceTest {

    @Mock
    CompetitionRepository competitionRepository;

    @Test
    public void givenCompetitionRepositoryWhenReqeustForAllEventsThenEventsWithDTOReturned() {
        givenCompetitionsCollection();

        EventsPageDTO eventsPageDTO = new EventsService(competitionRepository).getDataForView();

        assertThat(eventsPageDTO).isEqualTo(getExpectedEventsPageDTO());
    }

    public void givenCompetitionsCollection(){
        when(competitionRepository.findAllCompetitions()).thenReturn(getCompetitionsCollection());
    }

    private CompetitionsCollection getCompetitionsCollection() {
        CompetitionsCollection competitionsCollection = new CompetitionsCollection();
        competitionsCollection.add(new Competition(2L, "Competition1"));
        competitionsCollection.add(new Competition(44L, "Competition2"));
        competitionsCollection.add(new Competition(13L, "Competition3"));
        return  competitionsCollection;
    }

    private EventsPageDTO getExpectedEventsPageDTO(){
        EventsPageDTO eventsPageDTO = new EventsPageDTO();
        eventsPageDTO.setEvents(getCompetitionsCollection());
        return eventsPageDTO;
    }
}