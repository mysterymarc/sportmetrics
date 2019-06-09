package pl.mm.sportmetrics.dto.inputlayer;

import org.springframework.web.multipart.MultipartFile;

public class HtmlExternalDataMapper implements ExternalDataMapper{


    @Override
    public EventDTO readFile(MultipartFile file) {
        return new EventDTO();     //not a real implementation it's just a shell for training purposes
    }
}
