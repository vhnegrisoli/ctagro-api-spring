package br.com.ctagroapi.ctagroapi.modules.poligono.dto;

import br.com.ctagroapi.ctagroapi.modules.poligono.enums.ETipoConsulta;
import br.com.ctagroapi.ctagroapi.modules.poligono.model.Consultas;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
        var mapper = new ObjectMapper();
        var json = new Object();
        try {
            json = mapper.readValue(consultas.getConsultaJson(), Object.class);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return ConsultaResponse
            .builder()
            .id(consultas.getId())
            .tipoConsulta(consultas.getTipoConsulta())
            .poligono(PoligonoResponseApi.of(consultas.getPoligono()))
            .consulta(json)
            .build();
    }
}
