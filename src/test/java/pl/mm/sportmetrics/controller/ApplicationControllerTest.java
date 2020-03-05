package pl.mm.sportmetrics.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.mm.sportmetrics.domain.model.Competition;
import pl.mm.sportmetrics.domain.model.CompetitionsCollection;
import pl.mm.sportmetrics.domain.model.IdentifiersOfResultsGroupsCollection;
import pl.mm.sportmetrics.domain.model.Segments;
import pl.mm.sportmetrics.dto.viewlayer.*;
import pl.mm.sportmetrics.services.AnalysisService;
import pl.mm.sportmetrics.services.EventsService;
import pl.mm.sportmetrics.services.ImportService;
import pl.mm.sportmetrics.services.ResultsService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventsService eventsService;

    @MockBean
    private ResultsService resultsService;

    @MockBean
    private AnalysisService analysisService;

    @MockBean
    private ImportService importService;

    @Test
    public void givenApplicationWhenCallAddEventThenAddEventSiteNameReturned() throws Exception {
        this.mockMvc.perform(get("/addEvent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addEvent"));
    }

    @Test
    public void givenApplicationWhenCallEventsThenCorrectModelReturned() throws Exception {
        EventsPageDTO givenEventsPageDTO = exampleEventsPageDTO();
        when(eventsService.getDataForView()).thenReturn(givenEventsPageDTO);

        this.mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("allEvents"))
                .andExpect(model().attribute("model", givenEventsPageDTO));
    }

    @Test
    public void givenApplicationWhenCallWithoutEndpointThenRedirectionToEvents() throws Exception {
        this.mockMvc.perform(get(""))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/events"));
    }

    @Test
    public void givenCompetitionWhenCallResultsThenCorrectModelReturned() throws Exception {
        Long givenCompetitionId = 21L;
        ResultsPageDTO givenResultsPageDTO = exampleResultsPageDTO(givenCompetitionId);
        when(resultsService.getDataForView(givenCompetitionId)).thenReturn(givenResultsPageDTO);

        this.mockMvc.perform(get("/results")
                .param("competition_id", givenCompetitionId.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("results"))
                .andExpect(model().attribute("model", givenResultsPageDTO));
    }

    @Test
    public void givenCompetitionIncorrectWhenCallResultsThenErrorIsGenerated() throws Exception {
        Long givenCompetitionId = 11L;
        when(resultsService.getDataForView(givenCompetitionId))
                .thenThrow(new IllegalArgumentException("Repository doesn't return CompetitionEntity for competition_id=" + givenCompetitionId));

        this.mockMvc.perform(get("/results")
                .param("competition_id", givenCompetitionId.toString()))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Incorrect competitionId"));
    }

    @Test
    public void givenCompetitionAndCompetitorsGroupsWhenCallAnalyseThenCorrectModelReturned() throws Exception {
        Long givenCompetitionId = 21L;
        List<Long> givenCompetitorsGroup1 = Arrays.asList(12L,33L);
        List<Long> givenCompetitorsGroup2 = Arrays.asList(45L);
        IdentifiersOfResultsGroupsCollection givenGroupsCollection = new IdentifiersOfResultsGroupsCollection();
        givenGroupsCollection.add(givenCompetitorsGroup1);
        givenGroupsCollection.add(givenCompetitorsGroup2);
        AnalysisPageDTO givenAnalysisPageDTO = exampleAnalysisPageDTO(givenCompetitionId,givenGroupsCollection);
        when(analysisService.getDataForView(givenCompetitionId,givenGroupsCollection)).thenReturn(givenAnalysisPageDTO);

        this.mockMvc.perform(get("/analyse")
                .param("competitionId", givenCompetitionId.toString())
                .param("firstGroup", givenCompetitorsGroup1.get(0).toString(),givenCompetitorsGroup1.get(1).toString())
                .param("secondGroup", givenCompetitorsGroup2.get(0).toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("analyse"))
                .andExpect(model().attribute("model", givenAnalysisPageDTO));
    }

    @Test
    public void givenIncorrectParametersWhenCallAnalyseThenErrorIsGenerated() throws Exception {
        Long givenCompetitionId = 21L;
        List<Long> givenCompetitorsGroup1 = Arrays.asList(12L,33L);

        this.mockMvc.perform(get("/analyse")
                .param("competitionId", givenCompetitionId.toString())
                .param("firstGroup", givenCompetitorsGroup1.get(0).toString(),givenCompetitorsGroup1.get(1).toString())
                .param("secondGroup", ""))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Incorrect secondGroup"));
    }

    @Test
    public void givenMultipartFileWhenCallUploadWithSuccessThenCorrectModelReturned() throws Exception{
        MockMultipartFile file = new MockMultipartFile("file","".getBytes());

        when(importService.importExternalData(file)).thenReturn(true);

        this.mockMvc.perform(multipart("/uploadEventResults")
                .file(file))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("uploadResult", "success"))
                .andExpect(view().name("addEvent"));
    }

    @Test
    public void givenMultipartFileWhenCallUploadWithFailureThenFailureModelReturned() throws Exception{
        MockMultipartFile file = new MockMultipartFile("file","".getBytes());

        when(importService.importExternalData(file)).thenReturn(false);

        this.mockMvc.perform(multipart("/uploadEventResults")
                .file(file))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("uploadResult", "failure"))
                .andExpect(view().name("addEvent"));
    }

    private EventsPageDTO exampleEventsPageDTO() {
        CompetitionsCollection competitionsCollection = new CompetitionsCollection();
        competitionsCollection.add(new Competition(1L, "Example competition"));
        EventsPageDTO eventsPageDTO = new EventsPageDTO();
        eventsPageDTO.setEvents(competitionsCollection);
        return eventsPageDTO;
    }

    private ResultsPageDTO exampleResultsPageDTO(Long competitionId) {
        ResultsPageDTO resultsPageDTO = new ResultsPageDTO();
        resultsPageDTO.setCompetition(getCompetition(competitionId));
        resultsPageDTO.setSegments(getSegments());
        resultsPageDTO.setResultRows(getResultRows());
        return resultsPageDTO;
    }

    private RowResultsGroupView getResultRows(){
        RowResultsGroupView rowResultsGroupView = new RowResultsGroupView();
        rowResultsGroupView.add(getSingleRowResult());
        rowResultsGroupView.add(getSingleRowResult());
        return rowResultsGroupView;
    }

    private RowResultView getSingleRowResult() {
        return new RowResultView(25L, 3L, "Janusz Nosacz", "Wrocław", "2", "00:20", "+00:30",
                Arrays.asList(getSingleResult("00:20","1")),Arrays.asList(getSingleResult("00:40","2")));
    }

    private SingleResultView getSingleResult(String time, String position) {
        return new SingleResultView(time, position);
    }

    private Competition getCompetition(Long competitionId){
        return new Competition(competitionId,"Example competition");
    }

    private Segments getSegments(){
        return new Segments(Arrays.asList("1-2","3-5"));
    }

    private AnalysisPageDTO exampleAnalysisPageDTO(Long competitionId, IdentifiersOfResultsGroupsCollection groupsCollection) {
        return new AnalysisPageDTO(
                getCompetition(competitionId),
                getSegments(),
                expectedRowResultsGroupsCollectionView(),
                expectedAnalysisResultsGroupsCollectionView()
        );
    }

    private RowResultView expectedRowResultView() {
        return new RowResultView(
                25L,
                3L,
                "Janusz Nosacz",
                "Wrocław",
                "2",
                "00:20",
                "+00:30",
                Arrays.asList(new SingleResultView("00:20", "1")),
                Arrays.asList(new SingleResultView("00:40", "2"))
        );
    }

    private RowResultsGroupsColletionView expectedRowResultsGroupsCollectionView() {
        return new RowResultsGroupsColletionView(
                Arrays.asList(new RowResultsGroupView(
                                Arrays.asList(expectedRowResultView())),
                        new RowResultsGroupView( //because two groups were created
                                Arrays.asList(expectedRowResultView()))
                )
        );
    }

    private AnalysisResultsGroupsCollectionView expectedAnalysisResultsGroupsCollectionView(){
        return new AnalysisResultsGroupsCollectionView(
                Arrays.asList(new AnalysisResultsGroupView(
                                Arrays.asList(expectedAverageAnalysisResultRow())),
                        new AnalysisResultsGroupView( //because two groups were created
                                Arrays.asList(expectedAverageAnalysisResultRow())
                        ))
        );
    }

    private AnalysisResultRow expectedAverageAnalysisResultRow(){
        return new AnalysisResultRow(
                "Average Time",
                Arrays.asList(new AnalysisResultForSegment("00:20", "draw"))
        );
    }
}
