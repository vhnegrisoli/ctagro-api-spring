package br.com.ctagroapi.ctagroapi.modules.poligono.client;

import br.com.ctagroapi.ctagroapi.modules.poligono.dto.PoligonoRequest;
import br.com.ctagroapi.ctagroapi.modules.poligono.dto.PoligonoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "poligonoClient",
    url = "${app-config.integracao.poligono.url}"
)
public interface PoligonoClient {

    @PostMapping
    PoligonoResponse criarPoligono(@RequestBody PoligonoRequest request);
}
