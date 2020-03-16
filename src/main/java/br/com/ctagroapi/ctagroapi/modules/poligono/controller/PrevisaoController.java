package br.com.ctagroapi.ctagroapi.modules.poligono.controller;

import br.com.ctagroapi.ctagroapi.modules.poligono.service.PrevisaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("api/previsao/integracao")
public class PrevisaoController {

    @Autowired
    private PrevisaoService previsaoService;

    @GetMapping("atual/poligono/{polyid}")
    public Object buscarPrivsaoAtual(@PathVariable String polyid) {
        return previsaoService.buscarPrevisaoAtual(polyid);
    }

    @GetMapping("forecast/poligono/{polyid}")
    public Object buscarPrivsaoComForecast(@PathVariable String polyid) {
        return previsaoService.buscarPrevisaoComForecast(polyid);
    }

    @GetMapping("historico/poligono/{polyid}")
    public Object buscarPrivsaoComForecast(@PathVariable String polyid,
                                           @PathParam("start") String start,
                                           @PathParam("end") String end) {
        return previsaoService.buscarPrevisaoHistorica(polyid, start, end);
    }
}
