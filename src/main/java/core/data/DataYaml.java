package core.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Log4j2
public class DataYaml {

    private static File getYamlDataFile(String path){
        log.info(String.format("Pegando arquivo %s.yaml com attachment massa de dados do ambiente %s"
                ,path,System.getProperty("env")));
        return new File("./src/test/resources/data/"+System.getProperty("env")+"/"+path+".yml");
    }

    @SneakyThrows
    public static LinkedHashMap<String,String> getMapYamlValues(String fileName, String titulo){

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        Map<String , Object> maps;
        try {
            maps = (LinkedHashMap<String, Object>) mapper.readValue(getYamlDataFile(fileName), Map.class);
            log.info(String.format("Retornando objeto HashMap com massa de dados do arquivo %s com titulo %s",fileName,titulo));
            return  (LinkedHashMap<String, String>) maps.get(titulo);
        } catch (IOException e) {
            log.error("Erro ao tentar ler o arquivo de massa "+fileName+".yaml - stackTrace: " + e);
            throw new Exception(e);
        }
    }
}
