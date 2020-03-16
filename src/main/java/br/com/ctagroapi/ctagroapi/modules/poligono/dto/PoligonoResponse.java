package br.com.ctagroapi.ctagroapi.modules.poligono.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("MemberName")
public class PoligonoResponse {

    private String id;
    private GeoJson geo_json;
    private String user_id;
    private String name;
    private List<Double> center;
    private Double area;
}
