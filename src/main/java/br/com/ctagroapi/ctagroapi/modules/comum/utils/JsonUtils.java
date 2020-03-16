package br.com.ctagroapi.ctagroapi.modules.comum.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {

    public static Object stringJsonToObject(String json) {
        var mapper = new ObjectMapper();
        var jsonObject = new Object();
        try {
            jsonObject = mapper.readValue(json, Object.class);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return jsonObject;
    }

    public static String jsonObjectToString(Object json) {
        var mapper = new ObjectMapper();
        var stringJson = "";
        try {
            stringJson = mapper.writeValueAsString(json);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return stringJson;
    }
}
