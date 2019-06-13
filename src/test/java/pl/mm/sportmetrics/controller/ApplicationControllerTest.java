package pl.mm.sportmetrics.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.mm.sportmetrics.services.AnalysisService;
import pl.mm.sportmetrics.services.EventsService;
import pl.mm.sportmetrics.services.ImportService;
import pl.mm.sportmetrics.services.ResultsService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventsService service;

    @MockBean
    private ResultsService resultsService;

    @MockBean
    private AnalysisService analysisService;

    @MockBean
    private ImportService importService;

    @Test
    public void givenApplicationContextActiveWhenCallAddEventThenAddEventSiteNameReturned() throws Exception {
        this.mockMvc
                .perform(get("/addEvent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("addEvent"));
    }
}
