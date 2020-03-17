package br.com.ctagroapi.ctagroapi.modules.comum.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class JsonUtils {

    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

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

    public static List<String> castDateListFromUnixTime(String consultaJson) {
        var stringDtoJson = consultaJson.split("dt");
        var dateList = Arrays.asList(stringDtoJson);
        return IntStream
            .range(1, dateList.size())
            .mapToObj(i -> LocalDateTime
                .ofEpochSecond(getUnixTime(dateList.get(i)), 0, ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))
            )
            .collect(Collectors.toList());
    }

    private static Long getUnixTime(String index) {
        var unixTime = index.split(",")[0];
        unixTime = unixTime.replace("\":", "");
        return Long.parseLong(unixTime);
    }
}
