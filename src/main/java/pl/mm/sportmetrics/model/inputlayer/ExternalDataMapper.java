package pl.mm.sportmetrics.model.inputlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

public class ExternalDataMapper {

    public Event readJsonFile(MultipartFile file) {

        Event receivedEvent;
        try {
            InputStreamReader stream = new InputStreamReader(file.getInputStream(), "UTF-8");
            receivedEvent = new ObjectMapper().readValue(stream, Event.class);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return receivedEvent;
    }
}
