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
public class Geometry {

    private String type;
    private List<String> coordinates;
}
