package br.com.ctagroapi.ctagroapi.modules.poligono.dto;

import br.com.ctagroapi.ctagroapi.modules.poligono.enums.ETipoConsulta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static br.com.ctagroapi.ctagroapi.modules.comum.utils.JsonUtils.castDateListFromUnixTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrevisaoResponse {

    private Object consulta;
    private ETipoConsulta tipoConsulta;
    private List<String> datasPrevisao;

    public static PrevisaoResponse of(Object consulta, ETipoConsulta tipoConsulta, String consultaJson) {
        return PrevisaoResponse
            .builder()
            .consulta(consulta)
            .tipoConsulta(tipoConsulta)
            .datasPrevisao(castDateListFromUnixTime(consultaJson))
            .build();
    }
}
