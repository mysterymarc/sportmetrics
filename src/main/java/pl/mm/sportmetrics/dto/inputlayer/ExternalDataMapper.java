package pl.mm.sportmetrics.dto.inputlayer;

import org.springframework.web.multipart.MultipartFile;

public interface ExternalDataMapper {

    public Event readFile(MultipartFile file);

}
