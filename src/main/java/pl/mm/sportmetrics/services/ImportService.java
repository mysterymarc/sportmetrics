package pl.mm.sportmetrics.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.mm.sportmetrics.dto.inputlayer.EventDTO;
import pl.mm.sportmetrics.dto.inputlayer.ExternalDataMapperFactory;
import pl.mm.sportmetrics.repository.CompetitionRepository;
import pl.mm.sportmetrics.repository.RunnersResultRepository;
import pl.mm.sportmetrics.repository.SegmentRepository;
import pl.mm.sportmetrics.repository.entity.EventEntitiesSet;

import java.io.UncheckedIOException;

@Service
public class ImportService {

    private final RunnersResultRepository runnersResultRepository;
    private final CompetitionRepository competitionRepository;
    private final SegmentRepository segmentRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ImportService(RunnersResultRepository runnersResultRepository, CompetitionRepository competitionRepository, SegmentRepository segmentRepository) {
        this.runnersResultRepository = runnersResultRepository;
        this.competitionRepository = competitionRepository;
        this.segmentRepository = segmentRepository;
    }

    public boolean importExternalData(MultipartFile jsonFile){
        try {
            EventDTO receivedEventDTO = new ExternalDataMapperFactory().getMapper("json").readFile(jsonFile);
            storeEvent(receivedEventDTO);
            return true;
        } catch (UncheckedIOException e){
            logger.error("Data not uploaded to repository", e);
            return false;
        }
    }

    private void storeEvent(EventDTO eventDTO){
        EventEntitiesSet eventEntitiesSet = new EventEntitiesSet(eventDTO);
        competitionRepository.saveCompetition(eventEntitiesSet);
        segmentRepository.saveSegments(eventEntitiesSet);
        runnersResultRepository.saveRunnersResult(eventEntitiesSet);
    }
}
