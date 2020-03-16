package br.com.ctagroapi.ctagroapi.modules.poligono.dto;

import lombok.Data;

@Data
public class PoligonoRequest {

    private String name;
    private GeoJson geoJson;
}
