package br.com.ctagroapi.ctagroapi.modules.poligono.dto;

import br.com.ctagroapi.ctagroapi.modules.poligono.model.Poligono;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.com.ctagroapi.ctagroapi.modules.comum.utils.JsonUtils.stringJsonToObject;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoligonoResponseApi {

    private Integer id;
    private String poligonoId;
    private String userId;
    private String name;
    private Object geoJson;
    private List<Double> center;
    private Double area;
    private LocalDateTime createdAt;

    public static PoligonoResponseApi of(Poligono poligono) {
        var response = new PoligonoResponseApi();
        BeanUtils.copyProperties(poligono, response);
        response.setGeoJson(stringJsonToObject(poligono.getGeoJson()));
        response.setCenter(getCenterList(poligono.getCenter()));
        return response;
    }

    private static List<Double> getCenterList(String centerString) {
        centerString = centerString.replace("[", "").replace("]", "").replace(" ", "");
        var centerArray = centerString.split(",");
        var list = new ArrayList<Double>();
        for (int i = 0; i < centerArray.length; i++) {
            list.add(Double.parseDouble(centerArray[i]));
        }
        return list;
    }
}
