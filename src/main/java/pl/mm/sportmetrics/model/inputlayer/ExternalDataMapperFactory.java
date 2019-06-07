package pl.mm.sportmetrics.model.inputlayer;

public class ExternalDataMapperFactory {

    public ExternalDataMapper getMapper(String type){
        if(type.equals("json")){
            return new JsonExternalDataMapper();
        } else if (type.equals("html")){
            return new HtmlExternalDataMapper();
        }
        throw new IllegalArgumentException("Factory not found ExternalDataMapper implementation for type="+type);
    }

}
