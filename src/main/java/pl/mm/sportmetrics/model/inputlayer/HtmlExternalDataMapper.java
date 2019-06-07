package pl.mm.sportmetrics.model.inputlayer;

import org.springframework.web.multipart.MultipartFile;

public class HtmlExternalDataMapper implements ExternalDataMapper{


    @Override
    public Event readFile(MultipartFile file) {
        return new Event();     //not a real implementation it's just a shell for training purposes
    }
}
