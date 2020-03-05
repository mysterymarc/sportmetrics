package pl.mm.sportmetrics.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.mm.sportmetrics.domain.model.IdentifiersOfResultsGroupsCollection;
import pl.mm.sportmetrics.services.AnalysisService;
import pl.mm.sportmetrics.services.EventsService;
import pl.mm.sportmetrics.services.ImportService;
import pl.mm.sportmetrics.services.ResultsService;

import java.util.List;


@Controller
public class ApplicationController {

    private final EventsService eventsService;

    private final ResultsService resultsService;

    private final AnalysisService analysisService;

    private final ImportService importService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ApplicationController(EventsService eventsService, ResultsService resultsService, AnalysisService analysisService, ImportService importService){
        this.eventsService = eventsService;
        this.resultsService = resultsService;
        this.analysisService = analysisService;
        this.importService = importService;
    }

    @GetMapping(value = "/")
    public String redirectToMainPage() {
        return "redirect:/events";
    }

    @PostMapping(value = "/uploadEventResults")
    public String importEvent(@RequestParam("file") MultipartFile jsonFile, Model model) {
       boolean success = importService.importExternalData(jsonFile);
       model.addAttribute("uploadResult", success ? "success" : "failure");
       return "addEvent";
    }

    @GetMapping(value = "/addEvent")
    public String showAddEventForm() {
        return "addEvent";
    }

    @GetMapping(value = "/events")
    public String showEventsList(Model model) {
        model.addAttribute("model", eventsService.getDataForView());
        return "allEvents";
    }

    @GetMapping(value = "/results", params = "competition_id")
    public String showEventsResults(@RequestParam("competition_id") Long competitionId, Model model) {
        try {
            model.addAttribute("model", resultsService.getDataForView(competitionId));
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect competitionId");
        }
        return "results";
    }

    @GetMapping(value = "/analyse")
    public String showEventsAnalyses(@RequestParam Long competitionId,
                                 @RequestParam List<Long> firstGroup,
                                 @RequestParam List<Long> secondGroup,
                                 Model model) {

        if (firstGroup.size() == 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect firstGroup");
        if (secondGroup.size() == 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect secondGroup");

        IdentifiersOfResultsGroupsCollection groupsCollection = new IdentifiersOfResultsGroupsCollection();
        groupsCollection.add(firstGroup);
        groupsCollection.add(secondGroup);

        model.addAttribute("model", analysisService.getDataForView(competitionId, groupsCollection));
        return "analyse";
    }
}
