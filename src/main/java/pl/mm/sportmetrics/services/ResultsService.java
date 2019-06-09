package pl.mm.sportmetrics.services;

import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.domain.model.Competition;
import pl.mm.sportmetrics.domain.model.ResultsForRunnersGroup;
import pl.mm.sportmetrics.domain.model.Segments;
import pl.mm.sportmetrics.dto.viewlayer.ResultsMatrixFromBusinessToViewMapper;
import pl.mm.sportmetrics.dto.viewlayer.ResultsPageDTO;
import pl.mm.sportmetrics.dto.viewlayer.RowResultsGroupView;
import pl.mm.sportmetrics.repository.CompetitionRepository;
import pl.mm.sportmetrics.repository.RunnersResultRepository;
import pl.mm.sportmetrics.repository.SegmentRepository;


@Service
public class ResultsService {

    private final RunnersResultRepository runnersResultRepository;

    private final CompetitionRepository competitionRepository;

    private final SegmentRepository segmentRepository;

    public ResultsService(RunnersResultRepository runnersResultRepository, CompetitionRepository competitionRepository, SegmentRepository segmentRepository) {
        this.runnersResultRepository = runnersResultRepository;
        this.competitionRepository = competitionRepository;
        this.segmentRepository = segmentRepository;
    }

    public ResultsPageDTO getDataForView(Long competitionId) {
        ResultsPageDTO results = new ResultsPageDTO();
        results.setCompetition(getCompetition(competitionId));
        results.setSegments(getSegments(competitionId));
        results.setResultRows(getResults(competitionId));
        return results;
    }

    private Competition getCompetition(Long competitionId) {
        return competitionRepository.findCompetition(competitionId);
    }

    private Segments getSegments(Long competitionId) {
        return segmentRepository.findAllSegments(competitionId);
    }

    private RowResultsGroupView getResults(Long competitionId) {
        ResultsForRunnersGroup resultsForRunnersGroup = runnersResultRepository.findResultsByCompetitionId(competitionId);
        return mapBusinessResultsForGroupToViewResultsForGroup(resultsForRunnersGroup);
    }

    private RowResultsGroupView mapBusinessResultsForGroupToViewResultsForGroup(ResultsForRunnersGroup resultsForRunnersGroup){
        return new ResultsMatrixFromBusinessToViewMapper().doMapping(resultsForRunnersGroup);
    }
}
