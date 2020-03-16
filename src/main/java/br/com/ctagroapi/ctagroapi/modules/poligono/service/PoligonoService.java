package br.com.ctagroapi.ctagroapi.modules.poligono.service;

import br.com.ctagroapi.ctagroapi.modules.comum.exception.ValidacaoException;
import br.com.ctagroapi.ctagroapi.modules.poligono.dto.GeoJson;
import br.com.ctagroapi.ctagroapi.modules.poligono.dto.PoligonoRequest;
import br.com.ctagroapi.ctagroapi.modules.poligono.model.Poligono;
import br.com.ctagroapi.ctagroapi.modules.poligono.repository.PoligonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class PoligonoService {

    @Autowired
    private PoligonoRepository poligonoRepository;

    public Poligono salvarNovoPoligono(PoligonoRequest request) {
        validarCoordenadas(request.getGeoJson());
        return new Poligono();
    }

    private void validarCoordenadas(GeoJson geoJson){
        var coordenadas = geoJson.getGeometry().getCoordinates();
        if (isEmpty(coordenadas)) {
            throw new ValidacaoException("É obrigatório informar as coordenadas.");
        }
        var primeira = coordenadas.get(0);
        var ultima = coordenadas.get(coordenadas.size() - 1);
        if (!primeira.equals(ultima)) {
            throw new ValidacaoException("A primeira e a última coordenada devem ser iguais para formar um polígono.");
        }
    }
}
