package br.com.ctagroapi.ctagroapi.modules.poligono.controller;

import br.com.ctagroapi.ctagroapi.modules.poligono.dto.PoligonoRequest;
import br.com.ctagroapi.ctagroapi.modules.poligono.dto.PoligonoResponseApi;
import br.com.ctagroapi.ctagroapi.modules.poligono.service.PoligonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/poligono")
public class PoligonoController {

    @Autowired
    private PoligonoService poligonoService;

    @PostMapping
    public PoligonoResponseApi criarPoligono(@RequestBody PoligonoRequest request) {
        return poligonoService.salvarNovoPoligono(request);
    }

    @GetMapping
    public List<PoligonoResponseApi> buscarPoligonos() {
        return poligonoService.buscarPoligonos();
    }

    @GetMapping("{id}")
    public PoligonoResponseApi buscarPoligonoPorId(@PathVariable Integer id) {
        return poligonoService.buscarPoligonoPorId(id);
    }

    @GetMapping("id/{poligonoId}")
    public PoligonoResponseApi buscarPoligonoPorIdDoPoligono(@PathVariable String poligonoId) {
        return poligonoService.buscarPoligonoPorIdDoPoligono(poligonoId);
    }
}
