package br.com.ctagroapi.ctagroapi.modules.poligono.service;

import br.com.ctagroapi.ctagroapi.modules.poligono.client.PrevisaoClient;
import br.com.ctagroapi.ctagroapi.modules.poligono.dto.ConsultaResponse;
import br.com.ctagroapi.ctagroapi.modules.poligono.model.Consultas;
import br.com.ctagroapi.ctagroapi.modules.poligono.repository.ConsultasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.ctagroapi.ctagroapi.modules.comum.utils.JsonUtils.jsonObjectToString;
import static br.com.ctagroapi.ctagroapi.modules.poligono.enums.ETipoConsulta.*;

@Slf4j
@Service
public class PrevisaoService {

    @Autowired
    private ConsultasRepository consultasRepository;
    @Autowired
    private PrevisaoClient previsaoClient;
    @Autowired
    private PoligonoService poligonoService;
    @Value("${app-config.agromonitoring_api_key}")
    private String apiKey;

    public Object buscarPrevisaoAtual(String polyid) {
        var poligono = poligonoService.buscarPoligonoPorIdDoPoligono(polyid);
        var consultaAtual = previsaoClient.buscarPrevisaoAtual(polyid, apiKey);
        consultasRepository.save(Consultas.of(poligono.getId(), ATUAL, jsonObjectToString(consultaAtual)));
        return consultaAtual;
    }

    public Object buscarPrevisaoComForecast(String polyid) {
        var poligono = poligonoService.buscarPoligonoPorIdDoPoligono(polyid);
        var consultaForecast = previsaoClient.buscarPrevisaoComForecast(polyid, apiKey);
        consultasRepository.save(Consultas.of(poligono.getId(), FORECAST, jsonObjectToString(consultaForecast)));
        return consultaForecast;
    }

    public Object buscarPrevisaoHistorica(String polyid, String start, String end) {
        var poligono = poligonoService.buscarPoligonoPorIdDoPoligono(polyid);
        var consultaHistorica = previsaoClient.buscarPrevisaoHistorica(polyid, apiKey, start, end);
        consultasRepository.save(Consultas.of(poligono.getId(), HISTORICO, jsonObjectToString(consultaHistorica)));
        return consultaHistorica;
    }

    public List<ConsultaResponse> buscarConsultasPorPoligono(Integer poligonoId) {
        return consultasRepository
            .findByPoligonoId(poligonoId)
            .stream()
            .map(ConsultaResponse::of)
            .collect(Collectors.toList());
    }

    public List<ConsultaResponse> buscarConsultasRealizadas() {
        return consultasRepository
            .findAll()
            .stream()
            .map(ConsultaResponse::of)
            .collect(Collectors.toList());
    }
}
