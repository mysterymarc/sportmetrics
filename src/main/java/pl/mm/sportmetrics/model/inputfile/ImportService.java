package pl.mm.sportmetrics.model.inputfile;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.mm.sportmetrics.mapper.CompetitionResultSetToDAOMapper;
import pl.mm.sportmetrics.mapper.JsonFileToCompetitionResultSetMapper;
import pl.mm.sportmetrics.repository.RepositoryService;

import java.io.UncheckedIOException;

@Service
public class ImportService {

    @Autowired
    private RepositoryService repositoryService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean importFile(MultipartFile jsonFile){

        try {
            CompetitionResultSet resSet = new JsonFileToCompetitionResultSetMapper().doMapping(jsonFile);

            CompetitionResultSetToDAOMapper daoMapper = new CompetitionResultSetToDAOMapper();
            daoMapper.doMapping(resSet);
            repositoryService.saveCompetition(daoMapper.competition);
            repositoryService.saveSegments(daoMapper.segments);
            repositoryService.saveCompetitors(daoMapper.competitors);
            repositoryService.saveTotalResults(daoMapper.totalResults);
            repositoryService.savePartialResults(daoMapper.partialResults);
            return true;
        } catch (UncheckedIOException e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            throw new IllegalArgumentException("Data not uploaded to repository");
        }
    }

}
