package br.com.ctagroapi.ctagroapi.modules.poligono.dto;

import lombok.Data;

@Data
@SuppressWarnings("MemberName")
public class PoligonoRequest {
    private String name;
    private GeoJson geo_json;
}
