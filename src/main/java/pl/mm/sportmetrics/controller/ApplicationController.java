package pl.mm.sportmetrics.controller;

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
import pl.mm.sportmetrics.model.inputfile.ImportService;
import pl.mm.sportmetrics.model.repo.*;
import pl.mm.sportmetrics.model.viewobject.*;
import pl.mm.sportmetrics.repository.*;
import pl.mm.sportmetrics.statistics.Calculation;

import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ApplicationController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ResultsService resultsService;

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private ImportService importService;


    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping(value = "/")
    public String firstMethod() {
        return "redirect:/events";
    }

    @PostMapping(value = "/uploadEventResults")
    public String controlFile(@RequestParam("file") MultipartFile jsonFile, Model model) {
        try {
            if(importService.importFile(jsonFile)){
                model.addAttribute("uploadResult","success");
            }else{
                model.addAttribute("uploadResult","failure");
            }
        } catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            model.addAttribute("uploadResult","failure");
        }
        return "addEvent";
    }

    @GetMapping(value="/addEvent")
    public String showAddEventPage(){
        return "addEvent";
    }

    @GetMapping(value = "/events")
    public String eventList(Model model) {
        model.addAttribute("competitions", repositoryService.getAllCompetitions());
        return "allEvents";
    }

    @GetMapping(value = "/results", params = "competition_id")
    public String returnResult(@RequestParam("competition_id") Long competitionId, Model model) {
        try{
            model.addAttribute("model",resultsService.getDataForView(competitionId));
        } catch(IllegalArgumentException e){
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect competitionId");
        }
        return "results";
    }

    @GetMapping(value = "/analyse")
    public String analyseRequest(@RequestParam Long competitionId,
                                 @RequestParam List<String> firstGroup,
                                 @RequestParam List<String> secondGroup,
                                 Model model) {

        if (firstGroup.size() == 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect firstGroup");
        if (secondGroup.size() == 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect secondGroup");

        model.addAttribute("model", analysisService.getDataForView(competitionId,firstGroup,secondGroup));
        return "analyse";
    }

}
