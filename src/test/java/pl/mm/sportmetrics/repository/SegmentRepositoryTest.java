package pl.mm.sportmetrics.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.mm.sportmetrics.domain.model.Segments;
import pl.mm.sportmetrics.repository.dao.SegmentDAO;
import pl.mm.sportmetrics.repository.entity.CompetitionEntity;
import pl.mm.sportmetrics.repository.entity.SegmentEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SegmentRepositoryTest {

    @Mock
    private SegmentDAO segmentDao;

    @Test
    public void givenCompetitionIdWhenFindAllThenAllSegmentsReturned() {
        Long givenCompetitionId = 12L;

        when(segmentDao.findByCompetitionId(givenCompetitionId)).thenReturn(getExampleSegmentList(givenCompetitionId));

        Segments segments = new SegmentRepository(segmentDao).findAllSegments(givenCompetitionId);

        Assertions.assertThat(segments).isEqualTo(expectedSegments());
    }

    private Optional<List<SegmentEntity>> getExampleSegmentList(Long competitionId){
        List<SegmentEntity> segmentEntities = new ArrayList<>();
        segmentEntities.add(getSegmentEntity(1L, 1, "1-2", competitionId));
        segmentEntities.add(getSegmentEntity(2L, 2, "3-4", competitionId));
        segmentEntities.add(getSegmentEntity(3L, 3, "7-8", competitionId));
        return Optional.of(segmentEntities);
    }

    private SegmentEntity getSegmentEntity(Long id, int orderNumber, String name, Long competitionId){
        SegmentEntity segment = new SegmentEntity();
        segment.id = id;
        segment.orderNumber = orderNumber;
        segment.name = name;
        segment.competition = getCompetitionEntity(competitionId, "Example");
        return segment;
    }

    private CompetitionEntity getCompetitionEntity(Long id, String name) {
        CompetitionEntity competitionEntity = new CompetitionEntity();
        competitionEntity.id = id;
        competitionEntity.name = name;
        return competitionEntity;
    }

    private Segments expectedSegments() {
        return new Segments(Arrays.asList("1-2","3-4","7-8"));
    }

}