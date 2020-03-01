package pl.mm.sportmetrics.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.mm.sportmetrics.domain.model.IdentifiersOfResultsGroup;
import pl.mm.sportmetrics.domain.model.Result;
import pl.mm.sportmetrics.domain.model.ResultsForRunner;
import pl.mm.sportmetrics.domain.model.ResultsForRunnersGroup;
import pl.mm.sportmetrics.repository.dao.CompetitorDAO;
import pl.mm.sportmetrics.repository.dao.PartialResultDAO;
import pl.mm.sportmetrics.repository.dao.TotalResultDAO;
import pl.mm.sportmetrics.repository.entity.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RunnersResultRepositoryTest {

    @Mock
    private CompetitorDAO competitorDAO;

    @Mock
    private PartialResultDAO partialResultDao;

    @Mock
    private TotalResultDAO totalResultDao;

    @Test
    public void givenCompetitionIdWhenFindResultThenResultsReturned() {
        Long givenCompetitionId = 13L;
        when(totalResultDao.findByCompetitionId(givenCompetitionId)).thenReturn(givenTotalResultList(givenCompetitionId));
        when(partialResultDao.findByTotalResultId(anyLong())).thenAnswer(i -> givenPartialResultList(i.getArgument(0), givenCompetitionId));

        ResultsForRunnersGroup results = new RunnersResultRepository(competitorDAO, partialResultDao, totalResultDao).findResultsByCompetitionId(givenCompetitionId);

        Assertions.assertThat(results).isEqualTo(getExpectedResultsForRunnersGroup());
    }

    @Test
    public void givenTotalResultIdWhenFindResultThenResultsReturned() {
        IdentifiersOfResultsGroup identifiersOfResultsGroup = new IdentifiersOfResultsGroup();
        identifiersOfResultsGroup.add(1L);
        identifiersOfResultsGroup.add(2L);
        Long givenCompetitionId = 13L;
        when(totalResultDao.findById(anyLong())).thenAnswer(i -> Optional.of(givenTotalResultEntityBy(i.getArgument(0), givenCompetitionId)));
        when(partialResultDao.findByTotalResultId(anyLong())).thenAnswer(i -> givenPartialResultList(i.getArgument(0), givenCompetitionId));

        ResultsForRunnersGroup results = new RunnersResultRepository(competitorDAO, partialResultDao, totalResultDao).findResultsByTotalResultIds(identifiersOfResultsGroup);

        Assertions.assertThat(results).isEqualTo(getExpectedResultsForRunnersGroup());
    }


    private Optional<List<TotalResultEntity>> givenTotalResultList(Long competitionId) {
        List<TotalResultEntity> totalResultEntities = new ArrayList<>();
        totalResultEntities.add(givenTotalResultEntityBy(1L, competitionId));
        totalResultEntities.add(givenTotalResultEntityBy(2L, competitionId));
        return Optional.of(totalResultEntities);
    }

    private TotalResultEntity givenTotalResultEntityBy(Long id, Long competitionId) {
        String competitionName = "Example competition";
        if (id == 1L) {
            return givenTotalResultEntity(competitionId, competitionName, 1L,
                    "Kowalski", "London", 1L, 1, Time.valueOf("00:23:24"), Time.valueOf("00:00:00"));
        } else if (id == 2L) {
            return givenTotalResultEntity(competitionId, competitionName, 2L,
                    "Nowak", "Sydney", 2L, 2, Time.valueOf("00:27:24"), Time.valueOf("00:03:00"));
        } else {
            return givenTotalResultEntity(competitionId, competitionName, -1L,
                    "Unknown", "Unknown", -1L, -1, Time.valueOf("00:00:00"), Time.valueOf("00:00:00"));
        }
    }

    private TotalResultEntity givenTotalResultEntity(Long competitionId, String competitionName,
                                                     Long competitorId, String competitorName, String competitorCity,
                                                     Long id, int position, Time totalTime, Time delayTime) {
        TotalResultEntity totalResultEntity = new TotalResultEntity();
        totalResultEntity.competition = givenCompetitionEntity(competitionId, competitionName);
        totalResultEntity.competitor = givenCompetitorEntity(competitorId, competitorName, competitorCity);
        totalResultEntity.id = id;
        totalResultEntity.position = position;
        totalResultEntity.totalTime = totalTime;
        totalResultEntity.delayTime = delayTime;
        return totalResultEntity;
    }

    private CompetitionEntity givenCompetitionEntity(Long id, String name) {
        CompetitionEntity competitionEntity = new CompetitionEntity();
        competitionEntity.id = id;
        competitionEntity.name = name;
        return competitionEntity;
    }

    private CompetitorEntity givenCompetitorEntity(Long id, String name, String city) {
        CompetitorEntity competitorEntity = new CompetitorEntity();
        competitorEntity.id = id;
        competitorEntity.name = name;
        competitorEntity.city = city;
        return competitorEntity;
    }

    private Optional<List<PartialResultEntity>> givenPartialResultList(Long totalResultId, Long competitionId) {
        List<PartialResultEntity> partialResultEntities = new ArrayList<>();
        partialResultEntities.add(givenPartialResultEntity(totalResultId, competitionId));
        partialResultEntities.add(givenPartialResultEntity(totalResultId, competitionId));
        return Optional.of(partialResultEntities);
    }

    private PartialResultEntity givenPartialResultEntity(Long totalResultId, Long competitionId) {
        PartialResultEntity partialResultEntity = new PartialResultEntity();
        partialResultEntity.totalResult = givenTotalResultEntityBy(totalResultId, competitionId);
        partialResultEntity.segment = givenSegmentEntity(competitionId);
        partialResultEntity.id = 1L;
        partialResultEntity.segmentTime = Time.valueOf("00:00:25");
        partialResultEntity.cumulativeTime = Time.valueOf("00:01:25");
        partialResultEntity.segmentPosition = 1;
        partialResultEntity.cumulativePosition = 2;
        return  partialResultEntity;
    }

    private SegmentEntity givenSegmentEntity(Long competitionId) {
        SegmentEntity segmentEntity = new SegmentEntity();
        segmentEntity.id = 1L;
        segmentEntity.name = "1 - 2";
        segmentEntity.orderNumber = 1;
        segmentEntity.competition = givenCompetitionEntity(competitionId, "Example competition");
        return segmentEntity;
    }

    private ResultsForRunnersGroup getExpectedResultsForRunnersGroup() {
        ResultsForRunnersGroup results = new ResultsForRunnersGroup();
        results.add(new ResultsForRunner.Builder()
                .withName("Kowalski")
                .fromCity("London")
                .achievedPosition(1)
                .reachedTotalTime("00:23:24")
                .withDelayToWinner("00:00:00")
                .withSegmentResults(Arrays.asList(new Result("00:00:25", 1), new Result("00:00:25", 1)))
                .withCumulativeResults(Arrays.asList(new Result("00:01:25", 2), new Result("00:01:25", 2)))
                .competitorSignedById(1L)
                .totalResultSignedById(1L)
                .build());
        results.add(new ResultsForRunner.Builder()
                .withName("Nowak")
                .fromCity("Sydney")
                .achievedPosition(2)
                .reachedTotalTime("00:27:24")
                .withDelayToWinner("00:03:00")
                .withSegmentResults(Arrays.asList(new Result("00:00:25", 1), new Result("00:00:25", 1)))
                .withCumulativeResults(Arrays.asList(new Result("00:01:25", 2), new Result("00:01:25", 2)))
                .competitorSignedById(2L)
                .totalResultSignedById(2L)
                .build());
        return results;
    }
}