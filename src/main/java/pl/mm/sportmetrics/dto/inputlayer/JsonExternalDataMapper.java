package pl.mm.sportmetrics.dto.inputlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public class JsonExternalDataMapper implements ExternalDataMapper{

    @Override
    public Event readFile(MultipartFile file) {

        Event receivedEvent;
        try {
            InputStreamReader stream = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
            receivedEvent = new ObjectMapper().readValue(stream, Event.class);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return receivedEvent;
    }
}
