package br.com.ctagroapi.ctagroapi.modules.poligono.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoJson {

    private String type;
    private Object properties;
    private Geometry geometry;
}
