package pl.mm.sportmetrics.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.mm.sportmetrics.domain.model.IdentifiersOfResultsGroup;
import pl.mm.sportmetrics.domain.model.IdentifiersOfResultsGroupsCollection;
import pl.mm.sportmetrics.domain.model.ResultsForRunnersGroup;
import pl.mm.sportmetrics.repository.Repository;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnalysisServiceTest {

    @Mock
    private Repository repository;

    private AnalysisService analysisService;

    @Before
    public void setup() {
        analysisService = new AnalysisService(repository);
    }

    @Test
    public void getDataForView_returns_correct_data() {
        IdentifiersOfResultsGroupsCollection groups = givenCorrectGroupCollection();

        givenRepositoryReturnsResultsForGroups(groups);


        analysisService.getDataForView(1L, groups);
    }

    private IdentifiersOfResultsGroupsCollection givenCorrectGroupCollection() {
        IdentifiersOfResultsGroup groupId = new IdentifiersOfResultsGroup();
        groupId.add(3L);
        IdentifiersOfResultsGroupsCollection ids = new IdentifiersOfResultsGroupsCollection();
        ids.add(groupId);
        return ids;
    }

    private void givenRepositoryReturnsResultsForGroups(IdentifiersOfResultsGroupsCollection groups) {
        IdentifiersOfResultsGroup groupId = groups.iterator().next();
        when(repository.findResultsByTotalResultIds(groupId)).thenReturn(new ResultsForRunnersGroup());
    }

}
