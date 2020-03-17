package br.com.ctagroapi.ctagroapi.modules.poligono.controller;

import br.com.ctagroapi.ctagroapi.modules.poligono.dto.ConsultaResponse;
import br.com.ctagroapi.ctagroapi.modules.poligono.service.PrevisaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/consultas")
public class ConsultaController {

    @Autowired
    private PrevisaoService previsaoService;

    @GetMapping
    public List<ConsultaResponse> buscarTodas() {
        return previsaoService.buscarConsultasRealizadas();
    }

    @GetMapping("{id}")
    public ConsultaResponse buscarConsultaPorId(@PathVariable Integer id) {
        return previsaoService.buscarConsultaPorId(id);
    }

    @GetMapping("poligono/{poligonoId}")
    public List<ConsultaResponse> buscarTodasPorPoligono(@PathVariable Integer poligonoId) {
        return previsaoService.buscarConsultasPorPoligono(poligonoId);
    }
}
