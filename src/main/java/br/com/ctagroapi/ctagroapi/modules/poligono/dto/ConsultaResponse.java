package br.com.ctagroapi.ctagroapi.modules.poligono.dto;

import br.com.ctagroapi.ctagroapi.modules.poligono.enums.ETipoConsulta;
import br.com.ctagroapi.ctagroapi.modules.poligono.model.Consultas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static br.com.ctagroapi.ctagroapi.modules.comum.utils.JsonUtils.stringJsonToObject;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaResponse {

    private Integer id;
    private PoligonoResponseApi poligono;
    private ETipoConsulta tipoConsulta;
    private Object consulta;

    public static ConsultaResponse of(Consultas consultas) {
        return ConsultaResponse
            .builder()
            .id(consultas.getId())
            .tipoConsulta(consultas.getTipoConsulta())
            .poligono(PoligonoResponseApi.of(consultas.getPoligono()))
            .consulta(stringJsonToObject(consultas.getConsultaJson()))
            .build();
    }
}
