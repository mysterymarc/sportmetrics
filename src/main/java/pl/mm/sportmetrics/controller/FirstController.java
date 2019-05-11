package pl.mm.sportmetrics.controller;

import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import pl.mm.sportmetrics.mapper.*;
import pl.mm.sportmetrics.model.database.*;
import pl.mm.sportmetrics.model.inputfile.CompetitionResultSet;
import pl.mm.sportmetrics.model.repo.*;
import pl.mm.sportmetrics.model.viewobject.AnalysisResultRow;
import pl.mm.sportmetrics.model.viewobject.RowResultView;
import pl.mm.sportmetrics.model.viewobject.RowResultsGroupView;
import pl.mm.sportmetrics.model.viewobject.RowResultsGroupsColletionView;
import pl.mm.sportmetrics.repository.*;
import pl.mm.sportmetrics.statistics.Calculation;

import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class FirstController {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private CompetitorRepository competitorRepository;

    @Autowired
    private PartialResultRepository partialResultRepository;

    @Autowired
    private TotalResultRepository totalResultRepository;

    @Autowired
    private ResultsForRunnersGroupFactory resultsForRunnersGroupFactory;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping(value = "/")
    public String firstMethod() {
        return "redirect:/events";
    }

    @PostMapping(value = "/uploadEventResults")
    public String controlFile(@RequestParam("file") MultipartFile file, Model model) {

        try {
            CompetitionResultSet resSet = new JsonFileToCompetitionResultSetMapper().doMapping(file);

            CompetitionResultSetToDAOMapper daoMapper = new CompetitionResultSetToDAOMapper();
            daoMapper.doMapping(resSet);
            competitionRepository.save(daoMapper.competition);
            segmentRepository.saveAll(daoMapper.segments);
            competitorRepository.saveAll(daoMapper.competitors);
            totalResultRepository.saveAll(daoMapper.totalResults);
            partialResultRepository.saveAll(daoMapper.partialResults);
            model.addAttribute("uploadResult","success");
        } catch (UncheckedIOException e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            model.addAttribute("uploadResult","failure");
        }

        return "addEvent";
    }

    @GetMapping(value="/addEvent")
    public String showAddEventPage(){
        return "addEvent";
    }

    @GetMapping(value = "/events")
    public ModelAndView eventList() {
        Iterable<Competition> competitions = competitionRepository.findAll();
        return new ModelAndView("allEvents", "competitions", competitions);
    }

    @GetMapping(value = "/results", params = "competition_id")
    public String returnResult(@RequestParam("competition_id") Long competitionId, Model model) {
        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect competitionId"));
        model.addAttribute("competition", competition);

        List<Segment> segments = segmentRepository.findByCompetitionId(Long.valueOf(competitionId));
        model.addAttribute("segments", segments);

        ResultsForRunnersGroup resultsForRunnersGroup = resultsForRunnersGroupFactory.createUsingCompetitionId(competitionId);

        RowResultsGroupView detailResultRowForGroups;

        RepoToViewOfResultsMatrixMapper mapper = new RepoToViewOfResultsMatrixMapper();
        mapper.doMapping(resultsForRunnersGroup);
        detailResultRowForGroups = mapper.getResultsMatrix();


        model.addAttribute("resultRows", detailResultRowForGroups);

        return "results";
    }

    @GetMapping(value = "/analyse")
    public String analyseRequest(@RequestParam long competitionId,
                                 @RequestParam List<String> firstGroup,
                                 @RequestParam List<String> secondGroup,
                                 Model results) {

        Competition competition = competitionRepository.findById(Long.valueOf(competitionId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect competitionId"));

        if (firstGroup.size() == 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect firstGroup");
        if (secondGroup.size() == 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect secondGroup");

        results.addAttribute("competition", competition);

        List<Segment> segments = segmentRepository.findByCompetitionId(competition.getId());
        results.addAttribute("segments", segments);

        List<List<String>> resultIdsForGroups = new ArrayList<>();
        resultIdsForGroups.add(firstGroup);
        resultIdsForGroups.add(secondGroup);


        // TODO: model + coś co ten model buduje i zwraca (zwykle to by się nazywało Repository... ale z tym jest zamota
        // bo już masz Repository służące za DAO)
        // -> https://stackoverflow.com/questions/8550124/what-is-the-difference-between-dao-and-repository-patterns
        // -> https://blog.sapiensworks.com/post/2012/11/01/Repository-vs-DAO.aspx

        ResultsForRunnersGroupsCollection resultsForRunnersGroupsCollection = new ResultsForRunnersGroupsCollection();

        for (List<String> resultIds : resultIdsForGroups) {
            resultsForRunnersGroupsCollection.add(resultsForRunnersGroupFactory.createUsingTotalResultId(resultIds));
        }


        RowResultsGroupsColletionView detailResultRowForGroups = new RowResultsGroupsColletionView();

        for (ResultsForRunnersGroup group : resultsForRunnersGroupsCollection) {
            RepoToViewOfResultsMatrixMapper mapper = new RepoToViewOfResultsMatrixMapper();
            mapper.doMapping(group);
            detailResultRowForGroups.add(mapper.getResultsMatrix());
        }


        results.addAttribute("results", detailResultRowForGroups);

        SegmentsStatisticsGroupsCollection segmentsStatisticsGroupsCollection = new SegmentsStatisticsGroupsCollection();
        SegmentsStatisticsGroupFactory segmentsStatisticsGroupFactory = new SegmentsStatisticsGroupFactory();


        for (ResultsForRunnersGroup group : resultsForRunnersGroupsCollection) {
            segmentsStatisticsGroupsCollection.add(segmentsStatisticsGroupFactory.create(group));
        }

        new Calculation().setWinLossAttributeToStatisticsByItsComparison(
                segmentsStatisticsGroupsCollection.getGroup(0).getRow(0),
                segmentsStatisticsGroupsCollection.getGroup(1).getRow(0));


        List<List<AnalysisResultRow>> detailAnalysisRowForGroups = new ArrayList<>();

        for (SegmentsStatisticsGroup group : segmentsStatisticsGroupsCollection) {
            RepoToViewOfStatisticsMatrixMapper mapper = new RepoToViewOfStatisticsMatrixMapper();
            mapper.doMapping(group);
            detailAnalysisRowForGroups.add(mapper.getResultsMatrix());
        }

        results.addAttribute("avgAnalysis", detailAnalysisRowForGroups);
        return "analyse";
    }

}
