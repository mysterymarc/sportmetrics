package pl.mm.sportmetrics.repository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.mm.sportmetrics.domain.model.Competition;
import pl.mm.sportmetrics.domain.model.CompetitionsCollection;
import pl.mm.sportmetrics.repository.dao.CompetitionDAO;
import pl.mm.sportmetrics.repository.entity.CompetitionEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompetitionRepositoryTest {

    @Mock
    private CompetitionDAO competitionDAO;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void givenCompetitionIdWhenFindExecutedThenCompetitionReturned() {
        Long givenCompetitionId = 12L;
        String givenCompetitionName = "Example competition";
        when(competitionDAO.findById(givenCompetitionId)).thenReturn(getExampleCompetitionEntity(givenCompetitionId, givenCompetitionName));

        Competition competition = new CompetitionRepository(competitionDAO).findCompetition(givenCompetitionId);

        assertThat(competition).isEqualToComparingFieldByField(new Competition(givenCompetitionId, givenCompetitionName));
    }

    @Test
    public void givenCompetitionIdWhenFindExecutedAndCompetitionNotFoundThenExceptionThrown() {
        Long givenCompetitionId = 12L;
        when(competitionDAO.findById(anyLong())).thenReturn(Optional.empty());
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Repository doesn't return CompetitionEntity for competition_id=" + givenCompetitionId);

        new CompetitionRepository(competitionDAO).findCompetition(givenCompetitionId);
    }

    @Test
    public void givenCompetitionRepositoryWhenFindAllExecutedThenCompetitionsCollectionReturned() {
        Long firstCompetitionId = 12L;
        Long secondCompetitionId = 23L;
        String firstCompetitionName = "Example 1";
        String secondCompetitionName = "Example 2";
        List<Long> competitionsId = Arrays.asList(firstCompetitionId, secondCompetitionId);
        List<String> competitionsName = Arrays.asList(firstCompetitionName, secondCompetitionName);

        when(competitionDAO.findAll()).thenReturn(getCompetitionList(competitionsId,competitionsName));

        CompetitionsCollection collection = new CompetitionRepository(competitionDAO).findAllCompetitions();

        assertThat(collection).isEqualToComparingFieldByField(getCompetitions(competitionsId,competitionsName));
    }

    private Optional<CompetitionEntity> getExampleCompetitionEntity(Long competitionId, String competitionName) {
        CompetitionEntity competitionEntity = new CompetitionEntity();
        competitionEntity.id = competitionId;
        competitionEntity.name = competitionName;
        return Optional.of(competitionEntity);
    }

    private List<CompetitionEntity> getCompetitionList(List<Long> competitionsIds, List<String> competitionNames) {
        List<CompetitionEntity> competitions = new ArrayList<>();
        for (int i = 0; i < competitionsIds.size(); i++){
            competitions.add(getExampleCompetitionEntity(competitionsIds.get(i), competitionNames.get(i)).get());
        }
        return competitions;
    }

    private CompetitionsCollection getCompetitions(List<Long> competitionsIds, List<String> competitionNames){
        CompetitionsCollection collection = new CompetitionsCollection();
        for(int i= 0 ; i < competitionsIds.size(); i++){
            collection.add(new Competition(competitionsIds.get(i),competitionNames.get(i)));
        }
        return collection;
    }
}