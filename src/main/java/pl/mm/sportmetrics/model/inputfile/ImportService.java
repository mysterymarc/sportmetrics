package pl.mm.sportmetrics.model.inputfile;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.mm.sportmetrics.repository.Repository;

import java.io.UncheckedIOException;

@Service
public class ImportService {

    @Autowired
    private Repository repository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean importExternalData(MultipartFile jsonFile){

        try {
            EventDataCollection receivedEvent = new ExternalDataMapper().readJsonFile(jsonFile);
            repository.saveEventDataCollection(receivedEvent);
            return true;
        } catch (UncheckedIOException e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            throw new IllegalArgumentException("Data not uploaded to repository");
        }
    }

}
