package pl.mm.sportmetrics.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;
import pl.mm.sportmetrics.model.inputfile.CompetitionResultSet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

public class JsonFileToCompetitionResultSetMapper {

    public CompetitionResultSet doMapping(MultipartFile file) {

        CompetitionResultSet resSet;

        try {
            InputStreamReader stream = new InputStreamReader(file.getInputStream(), "UTF-8");

            ObjectMapper mapper = new ObjectMapper();

            resSet = mapper.readValue(stream, CompetitionResultSet.class);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return resSet;
    }
}
