package br.com.ctagroapi.ctagroapi.modules.poligono.service;

import br.com.ctagroapi.ctagroapi.modules.comum.exception.ValidacaoException;
import br.com.ctagroapi.ctagroapi.modules.poligono.client.PoligonoClient;
import br.com.ctagroapi.ctagroapi.modules.poligono.dto.GeoJson;
import br.com.ctagroapi.ctagroapi.modules.poligono.dto.PoligonoRequest;
import br.com.ctagroapi.ctagroapi.modules.poligono.dto.PoligonoResponse;
import br.com.ctagroapi.ctagroapi.modules.poligono.dto.PoligonoResponseApi;
import br.com.ctagroapi.ctagroapi.modules.poligono.model.Poligono;
import br.com.ctagroapi.ctagroapi.modules.poligono.repository.PoligonoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Service
public class PoligonoService {

    @Autowired
    private PoligonoRepository poligonoRepository;
    @Autowired
    private PoligonoClient poligonoClient;

    public PoligonoResponseApi salvarNovoPoligono(PoligonoRequest request) {
        validarCoordenadas(request.getGeo_json());
        var poligonoCriado = salvarPoligonoComIntegracao(request);
        return PoligonoResponseApi.of(poligonoRepository.save(Poligono.of(poligonoCriado)));
    }

    private PoligonoResponse salvarPoligonoComIntegracao(PoligonoRequest request) {
        try {
            return poligonoClient.criarPoligono(request);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }

    private void validarCoordenadas(GeoJson geoJson) {
        var coordenadas = geoJson.getGeometry().getCoordinates().get(0);
        if (isEmpty(coordenadas)) {
            throw new ValidacaoException("É obrigatório informar as coordenadas.");
        }
        var primeira = coordenadas.get(0).get(0);
        var ultima = coordenadas.get(coordenadas.size() - 1).get(0);
        if (!primeira.equals(ultima)) {
            throw new ValidacaoException("A primeira e a última coordenada devem ser iguais para formar um polígono.");
        }
    }

    public List<PoligonoResponseApi> buscarPoligonos() {
        return poligonoRepository
            .findAll()
            .stream()
            .map(PoligonoResponseApi::of)
            .collect(Collectors.toList());
    }

    public PoligonoResponseApi buscarPoligonoPorId(Integer id) {
        return PoligonoResponseApi.of(poligonoRepository.findById(id)
            .orElseThrow(() -> new ValidacaoException("O polígono não existe.")));
    }

    public PoligonoResponseApi buscarPoligonoPorIdDoPoligono(String poligonoId) {
        return PoligonoResponseApi.of(poligonoRepository.findByPoligonoId(poligonoId)
            .orElseThrow(() -> new ValidacaoException("O polígono não existe.")));
    }
}
