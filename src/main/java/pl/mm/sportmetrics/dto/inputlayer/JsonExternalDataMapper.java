package pl.mm.sportmetrics.dto.inputlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public class JsonExternalDataMapper implements ExternalDataMapper{

    @Override
    public EventDTO readFile(MultipartFile file) {

        EventDTO receivedEventDTO;
        try {
            InputStreamReader stream = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
            receivedEventDTO = new ObjectMapper().readValue(stream, EventDTO.class);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return receivedEventDTO;
    }
}
