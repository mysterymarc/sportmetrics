package pl.mm.sportmetrics.dto.inputlayer;

import org.springframework.stereotype.Component;

@Component
public class ExternalDataMapperFactory {

    public ExternalDataMapper getMapper(InputFileType type){
        if (type.equals(InputFileType.JSON)){
            return new JsonExternalDataMapper();
        } else if (type.equals(InputFileType.HTML)){
            return new HtmlExternalDataMapper();
        }
        throw new IllegalArgumentException("Factory not found ExternalDataMapper implementation for type=" + type);
    }

}
