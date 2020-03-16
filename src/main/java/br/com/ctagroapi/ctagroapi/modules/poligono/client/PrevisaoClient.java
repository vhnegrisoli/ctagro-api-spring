package br.com.ctagroapi.ctagroapi.modules.poligono.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "previsaoClient",
    url = "${app-config.integracao.previsao.url}"
)
public interface PrevisaoClient {

    @GetMapping
    Object buscarPrevisaoAtual(@RequestParam("polyid") String polyid, @RequestParam("appid") String appid);

    @GetMapping("history")
    Object buscarPrevisaoHistorica(@RequestParam("polyid") String polyid, @RequestParam("appid") String appid,
                                   @RequestParam("start") String start, @RequestParam("end") String end);

    @GetMapping("forecast")
    Object buscarPrevisaoComForecast(@RequestParam("polyid") String polyid, @RequestParam("appid") String appid);
}
