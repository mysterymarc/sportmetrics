package pl.mm.sportmetrics.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.mm.sportmetrics.model.inputlayer.Event;
import pl.mm.sportmetrics.model.inputlayer.ExternalDataMapper;
import pl.mm.sportmetrics.repository.Repository;

import java.io.UncheckedIOException;

@Service
public class ImportService {

    private Repository repository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ImportService(Repository repository){
        this.repository = repository;
    }

    public boolean importExternalData(MultipartFile jsonFile){
        try {
            Event receivedEvent = new ExternalDataMapper().readJsonFile(jsonFile);
            repository.saveEvent(receivedEvent);
            return true;
        } catch (UncheckedIOException e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            throw new IllegalArgumentException("Data not uploaded to repository");
        }
    }

}
