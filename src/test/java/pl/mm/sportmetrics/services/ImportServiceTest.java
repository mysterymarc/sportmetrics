package pl.mm.sportmetrics.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.mm.sportmetrics.dto.inputlayer.*;
import pl.mm.sportmetrics.repository.CompetitionRepository;
import pl.mm.sportmetrics.repository.RunnersResultRepository;
import pl.mm.sportmetrics.repository.SegmentRepository;
import pl.mm.sportmetrics.repository.entity.EventEntitiesSet;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImportServiceTest {

    @Mock
    RunnersResultRepository runnersResultRepository;

    @Mock
    CompetitionRepository competitionRepository;

    @Mock
    SegmentRepository segmentRepository;

    @Mock
    ExternalDataMapperFactory externalDataMapperFactory;

    @Mock
    ExternalDataMapper externalDataMapper;

    @Test
    public void givenJsonFileWhenImportSucceededThenDataShouldBeStoredInRepository() {
        MockMultipartFile givenJsonFile = new MockMultipartFile("example.json","".getBytes());
        givenMapperConvertingJsonFileToEventDTO();

        boolean importResult = new ImportService(runnersResultRepository,competitionRepository,segmentRepository, externalDataMapperFactory)
                .importExternalData(givenJsonFile);

        verify(competitionRepository).saveCompetition(expectedEventEntitiesSet());
        verify(segmentRepository).saveSegments(expectedEventEntitiesSet());
        verify(runnersResultRepository).saveRunnersResult(expectedEventEntitiesSet());
        assertTrue(importResult);
    }

    @Test
    public void givenJsonFileWhenImportFailedThenException(){
        MockMultipartFile givenJsonFile = new MockMultipartFile("example.json","".getBytes());
        givenMapperThrowingException();

        boolean importResult = new ImportService(runnersResultRepository,competitionRepository,segmentRepository, externalDataMapperFactory)
                .importExternalData(givenJsonFile);

        assertFalse(importResult);
    }

    private void givenMapperConvertingJsonFileToEventDTO() {
        givenDataMapperFactory();
        when(externalDataMapper.readFile(any(MultipartFile.class))).thenReturn(givenEventDTO());
    }

    private void givenMapperThrowingException() {
        givenDataMapperFactory();
        when(externalDataMapper.readFile(any(MultipartFile.class))).thenThrow(new UncheckedIOException(new IOException()));
    }

    private void givenDataMapperFactory(){
        when(externalDataMapperFactory.getMapper(any(InputFileType.class))).thenReturn(externalDataMapper);
    }

    private EventDTO givenEventDTO(){
        EventDTO eventDTO = new EventDTO();
        eventDTO.name = "Example event";
        eventDTO.segments = Arrays.asList("1-2", "2-3");
        eventDTO.results = Arrays.asList(getSingleResultSet(), getSingleResultSet());
        return eventDTO;
    }

    private SingleResultSet getSingleResultSet() {
        SingleResultSet singleResultSet = new SingleResultSet();
        singleResultSet.competitor = "Jan Kowalski";
        singleResultSet.city = "Wrocław";
        singleResultSet.position = 1;
        singleResultSet.total = "17:05";
        singleResultSet.delay = "00:35";
        singleResultSet.cumulativeResults = Arrays.asList(getAtomResult(), getAtomResult());
        singleResultSet.segmentResults = Arrays.asList(getAtomResult(),getAtomResult());
        return singleResultSet;
    }

    private AtomResult getAtomResult(){
        AtomResult atomResult = new AtomResult();
        atomResult.time  = "05:30";
        atomResult.position = 2;
        return atomResult;
    }

    private EventEntitiesSet expectedEventEntitiesSet() {
        return new EventEntitiesSet(givenEventDTO());
    }
}